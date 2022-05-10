package utilities;

import model.Currency;
import model.UserAccount;
import serviceImpl.CurrencyOps;

import java.math.BigDecimal;
import java.util.concurrent.locks.ReentrantLock;

public class Helper2 implements Runnable {
    UserAccount userAccount;
    ReentrantLock lock;
    CurrencyOps currencyOps = new CurrencyOps();

    public Helper2(UserAccount userAccount, ReentrantLock lock) {
        this.userAccount = userAccount;
        this.lock = lock;
    }

    @Override
    public void run() {
        lock.lock();
        try {
            System.out.println("Hello from " + Thread.currentThread().getName());
            currencyOps.buy(userAccount, Currency.HRYVNYA, Currency.EURO, BigDecimal.valueOf(10));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}
