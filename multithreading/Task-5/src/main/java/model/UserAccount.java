package model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.List;

public class UserAccount {
    private String name;
    private EnumMap<Currency, BigDecimal> wallet;
    private List<String> transactionHistory;
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String toString() {
        return "UserAccount{" +
                "name='" + name + '\'' +
                ", wallet=" + wallet +
                "};";
    }

    public String writeJson(UserAccount userAccount) throws JsonProcessingException {
        return objectMapper.writeValueAsString(userAccount);
    }



    public UserAccount() {
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
