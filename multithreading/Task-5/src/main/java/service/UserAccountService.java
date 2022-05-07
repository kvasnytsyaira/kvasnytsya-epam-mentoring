package service;

public interface UserAccountService {
    String getUserAccountStatus(String accountName);

    String getTransactionHistory(String accountName);

}
