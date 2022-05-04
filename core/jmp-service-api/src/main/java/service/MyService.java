package service;

import models.BankCard;
import models.Subscription;
import models.User;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.function.Predicate;

public interface MyService {
    void subscribe(BankCard bank_card);

    Subscription getSubscriptionByBankCardNumber(String number);

    List<User> getAllUsers();

    List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> predicate);

    default double getAverageUsersAge() {
        return getAllUsers()
                .stream()
                .mapToLong(user -> ChronoUnit.YEARS.between(user.getBirthday(), LocalDate.now()))
                .average()
                .orElse(0);

    }

    static boolean isPayableUser(User user) {
        var between = ChronoUnit.YEARS.between(user.getBirthday(), LocalDate.now());
        return between >= 18;
    }

}
