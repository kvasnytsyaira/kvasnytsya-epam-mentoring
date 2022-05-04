package models;

import java.time.LocalDate;

public class Subscription {
    String bankCard;
    LocalDate startDate;


    public Subscription(String bankCard, LocalDate startDate) {
        this.bankCard = bankCard;
        this.startDate = startDate;
    }

    public Subscription() {
    }

    public String getBankCard() {
        return bankCard;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "bankCard='" + bankCard + '\'' +
                ", startDate=" + startDate +
                '}';
    }
}
