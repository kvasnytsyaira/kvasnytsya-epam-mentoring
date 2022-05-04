package repository;

import models.BankCard;
import models.DebitBankCard;
import models.User;

import java.util.ArrayList;
import java.util.List;

public class BankCardRepositoryLike {
    private final List<BankCard> cards;

    public BankCardRepositoryLike() {
        this.cards = init();
    }

    public List<BankCard> getAllBankCards() {
        return cards;
    }

    public BankCard createBankCard(BankCard newCard) {
        cards.add(newCard);
        return newCard;
    }

    public void deleteBankCard(BankCard oldCard) {
        cards.remove(oldCard);
    }


    private List<BankCard> init() {
        UserRepositoryLike userRepositoryLike = new UserRepositoryLike();
        List<User> users = userRepositoryLike.getUsers();

        List<BankCard> cards = new ArrayList<>();
        cards.add(new BankCard(users.get(0)));
        cards.add(new BankCard(users.get(1)));
        cards.add(new BankCard(users.get(2)));
        cards.add(new BankCard(users.get(3)));
        cards.add(new DebitBankCard(users.get(4)));
        cards.add(new BankCard(users.get(5)));
        cards.add(new BankCard(users.get(6)));
        return cards;
    }


}
