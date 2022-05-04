import layerImpl.BankImpl;
import models.BankCardType;
import models.BankCard;
import models.Subscription;
import models.User;
import repository.UserRepositoryLike;
import service.MyService;
import serviceImpl.ServiceImpl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        UserRepositoryLike userRepositoryLike = new UserRepositoryLike();
        List<User> users = userRepositoryLike.getUsers();
        BankImpl bank = new BankImpl();
        ServiceImpl service = new ServiceImpl();

        //1.Create BankCard
        System.out.println("1.Create BankCard");
        BankCard bankCard = bank.createBankCard(users.get(0), BankCardType.DEBIT);
        System.out.println(bankCard.toString());

        //2.Subscribe
        System.out.println("2.Subscribe");
        service.subscribe(bankCard);

        //3.Get subscription by card number
        System.out.println("3.Get subscription by card number");
        Subscription subscriptionByBankCardNumber =
                service.getSubscriptionByBankCardNumber(bankCard.getNumber());
        System.out.println(subscriptionByBankCardNumber.toString());

        //4.Get all users
        System.out.println("4.Get all users");
        List<User> userList = userRepositoryLike.getUsers();
        userList.forEach(System.out::println);

        //5.Is payable user
        System.out.println("5.Is payable user");
        boolean payableUser = MyService.isPayableUser(userList.get(0));
        System.out.println(payableUser);

        //6. Get average age of users
        System.out.println("6. Get average age of users");
        double averageUsersAge = service.getAverageUsersAge();
        System.out.println(averageUsersAge);

        //7. Get all subscriptions older than 2 years
        System.out.println("7. Get all subscriptions older than 2 years");
        List<Subscription> allSubscriptionsByCondition = service.getAllSubscriptionsByCondition
                (subscription -> ChronoUnit.YEARS.between(subscription.getStartDate(), LocalDate.now()) >= 2);
        System.out.println(allSubscriptionsByCondition.size());
    }
}
