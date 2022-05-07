package utilities;

import model.Currency;
import model.UserAccount;
import service.UserAccountService;
import serviceImpl.CurrencyOps;
import serviceImpl.UserAccountImpl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.concurrent.locks.ReentrantLock;

public class Helper implements Runnable {
    UserAccount userAccount;
    ReentrantLock lock;
    UserAccountService userAccountService = new UserAccountImpl();
    CurrencyOps currencyOps = new CurrencyOps();

    public Helper(UserAccount userAccount, ReentrantLock lock) {
        this.userAccount = userAccount;
        this.lock = lock;
    }

    @Override
    public void run() {
        lock.lock();
        try {
            System.out.println("Hello from run Helper " + Thread.currentThread().getName());
            userAccountService.getUserAccountStatus(userAccount.getName());
            currencyOps.buy(userAccount, Currency.DOLLAR, Currency.HRYVNYA, BigDecimal.valueOf(10));
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}
