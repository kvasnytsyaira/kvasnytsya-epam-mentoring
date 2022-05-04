package serviceImpl;

import exceptions.IncorrectCardNumberException;
import models.BankCard;
import models.Subscription;
import models.User;
import repository.BankCardRepositoryLike;
import repository.SubscriptionRepositoryLike;
import repository.UserRepositoryLike;
import service.MyService;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ServiceImpl implements MyService {
    BankCardRepositoryLike bankCardRepository;

    UserRepositoryLike userRepository = new UserRepositoryLike();

    SubscriptionRepositoryLike subscriptionRepository = new SubscriptionRepositoryLike();


    @Override
    public void subscribe(BankCard bank_card) {
        subscriptionRepository.subscribe(new Subscription(bank_card.getNumber(), LocalDate.now()));
    }

    @Override
    public Subscription getSubscriptionByBankCardNumber(String number) {
        return subscriptionRepository.findByBankCardNumber(number)
                .orElseThrow(() -> new IncorrectCardNumberException("No Bank Card with number: " + number));
    }//todo

    @Override
    public List<User> getAllUsers() {
        return userRepository.getUsers();

    }

    @Override
    public List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> predicate) {
        return subscriptionRepository.getAll()
                .stream()
                .filter(predicate)
                .collect(Collectors.toUnmodifiableList());
    }
}
