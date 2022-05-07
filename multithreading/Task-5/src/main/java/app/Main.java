package app;

import model.Currency;
import model.UserAccount;
import service.ExchangeRateService;
import serviceImpl.CurrencyOps;
import serviceImpl.ExchangeRateImpl;
import serviceImpl.UserAccountImpl;
import utilities.Helper;
import utilities.UserAccountScanner;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    //    Make an application that contains business logic for making exchange operations between different currencies.
//
//    Create models for dealing with currencies, user accounts and exchange rates. One account can have multiple currency values.
//    Use BigDecimal for performing of exchange calculations.
//    Data with user accounts should be stored as files (one file per account).
//    Separate application functionality to DAO, service and utilities.
//    Create module which will provide high-level operations (manage accounts, currencies, exchange rates).
//    Create sample accounts and currencies. Define sample exchange rates.

    //    Provide concurrent data access to user accounts.
//    Simulate simultaneous currency exchanges for single account by multiple threads and ensure that all the operations are thread-safe.
//    Use ExecutorService to manage threads.
//    Make custom exceptions to let user to know the reason of error. Do not handle runtime exceptions.
//    Validate inputs such an account existence, sufficiency of currency amount, etc.
//    Log information about what is happening on different application levels and about conversion results. Use Logger for that.
    public static void main(String[] args) throws InterruptedException {
        UserAccountImpl userAccountService = new UserAccountImpl();
        CurrencyOps currencyOps = new CurrencyOps();
        currencyOps.init();

        EnumMap<Currency, BigDecimal> account1 = new EnumMap<>(Currency.class);
        account1.put(Currency.DOLLAR, BigDecimal.valueOf(1000));
        account1.put(Currency.EURO, BigDecimal.valueOf(150));
        account1.put(Currency.HRYVNYA, BigDecimal.valueOf(1500000));
        userAccountService.getUserAccountStatus("account1");
        UserAccount userAccount = new UserAccount("Account1", account1);

        UserAccountScanner userAccountScanner = new UserAccountScanner();
        userAccountScanner.readUserAccount("account1");
        ReentrantLock locker = new ReentrantLock();

        ExecutorService service = Executors.newFixedThreadPool(5);
        service.submit(new Helper(userAccount, locker));
//        for (int i = 1; i < 6; i++){
//
//            Thread t = new Thread(new Helper(userAccount, locker));
//            t.setName("Thread "+ i);
//            t.start();
//        }

        TimeUnit.SECONDS.sleep(10);
        service.shutdown();
    }

}
