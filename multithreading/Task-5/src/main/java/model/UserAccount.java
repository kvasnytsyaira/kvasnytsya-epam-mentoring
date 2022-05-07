package model;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.List;

public class UserAccount {
    private String name;
    private EnumMap<Currency, BigDecimal> wallet;
    private List<String> transactionHistory;

    @Override
    public String toString() {
        return "UserAccount{" +
                "name='" + name + '\'' +
                ", wallet=" + wallet +
                '}';
    }

    public UserAccount(String name, EnumMap<Currency, BigDecimal> wallet) {
        this.name = name;
        this.wallet = wallet;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EnumMap<Currency, BigDecimal> getWallet() {
        return wallet;
    }


    public List<String> getTransactionHistory() {
        return transactionHistory;
    }

    public void setTransactionHistory(List<String> transactionHistory) {
        this.transactionHistory = transactionHistory;
    }
}
