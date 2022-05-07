package serviceImpl;

import service.UserAccountService;
import utilities.UserAccountScanner;

public class UserAccountImpl implements UserAccountService {
    @Override
    public String getUserAccountStatus(String accountName) {
        UserAccountScanner userAccountScanner = new UserAccountScanner();
        return userAccountScanner.readUserAccountStatus(accountName);
    }


    @Override
    public String getTransactionHistory(String accountName) {
        UserAccountScanner userAccountScanner = new UserAccountScanner();
        return userAccountScanner.readUserAccountTransactionHistory(accountName);
    }

}

