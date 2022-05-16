package model;

import java.util.UUID;

public class BankCard {

    String number;
    User user;

    public BankCard(User user) {
        this.number = String.valueOf(UUID.randomUUID());
        this.user = user;
    }

    public BankCard() {
    }

    public String getNumber() {
        return number;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "BankCard{" +
                "number='" + number + '\'' +
                ", user=" + user +
                '}';
    }
}
