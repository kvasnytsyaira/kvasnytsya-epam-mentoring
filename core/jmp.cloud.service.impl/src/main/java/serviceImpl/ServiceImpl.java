package serviceImpl;

import model.BankCard;
import model.Subscription;
import model.User;
import repository.BankCardRepositoryLike;
import repository.SubscriptionRepositoryLike;
import repository.UserRepositoryLike;
import service.MyService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
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
    public Optional<Subscription> getSubscriptionByBankCardNumber(String number) {
        return subscriptionRepository.findByBankCardNumber(number);
    }

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
