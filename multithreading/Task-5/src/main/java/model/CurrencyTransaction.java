package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CurrencyTransaction {
    private UserAccount userAccount;
    private Currency from;
    private Currency to;
    private BigDecimal amount;
    private LocalDateTime transactionDateTime;

    @Override
    public String toString() {
        return "CurrencyTransaction{userAccount=" + userAccount +
                ", from=" + from +
                ", to=" + to +
                ", amount=" + amount +
                ", transactionDateTime=" + transactionDateTime + '}' +
                "\n";
    }

    public CurrencyTransaction(UserAccount userAccount, Currency from, Currency to, BigDecimal amount) {
        this.userAccount = userAccount;
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.transactionDateTime = LocalDateTime.now();
    }

    public CurrencyTransaction() {
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public Currency getFrom() {
        return from;
    }

    public void setFrom(Currency from) {
        this.from = from;
    }

    public Currency getTo() {
        return to;
    }

    public void setTo(Currency to) {
        this.to = to;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getTransactionDateTime() {
        return transactionDateTime;
    }
}
