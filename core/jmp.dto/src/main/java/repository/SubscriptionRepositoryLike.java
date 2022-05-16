package repository;

import model.BankCard;
import model.Subscription;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SubscriptionRepositoryLike {
    private final List<Subscription> subscriptions;

    public SubscriptionRepositoryLike() {
        this.subscriptions = init();
    }

    public void subscribe(Subscription subscription) {
        subscriptions.add(subscription);
    }

    public Optional<Subscription> findByBankCardNumber(String number) {
        return subscriptions.stream()
                .filter(subscription -> subscription.getBankCard().equals(number))
                .findFirst();
    }

    public List<Subscription> getAll(){
        return subscriptions;
    }

    private List<Subscription> init() {
        BankCardRepositoryLike bankCardRepositoryLike = new BankCardRepositoryLike();
        List<BankCard> allBankCards = bankCardRepositoryLike.getAllBankCards();

        List<Subscription> subscriptions = new ArrayList<>();
        subscriptions.add(new Subscription(allBankCards.get(0).getNumber(), LocalDate.of(2021, 1, 12)));
        subscriptions.add(new Subscription(allBankCards.get(1).getNumber(), LocalDate.of(2006, 1, 12)));
        subscriptions.add(new Subscription(allBankCards.get(2).getNumber(), LocalDate.of(2005, 1, 12)));
        subscriptions.add(new Subscription(allBankCards.get(3).getNumber(), LocalDate.of(2004, 1, 12)));
        subscriptions.add(new Subscription(allBankCards.get(4).getNumber(), LocalDate.of(2002, 1, 12)));
        subscriptions.add(new Subscription(allBankCards.get(5).getNumber(), LocalDate.of(2003, 1, 12)));
        return subscriptions;
    }

}
