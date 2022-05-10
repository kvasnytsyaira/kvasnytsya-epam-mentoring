package serviceImpl;

import model.Currency;
import model.CurrencyTransaction;
import model.UserAccount;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import service.ExchangeRateService;
import utilities.SameCurrencyExchangeException;
import utilities.UserAccountWriter;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.EnumMap;

import static model.Currency.*;
public class CurrencyOps {
    private final static Logger logger = Logger.getLogger(CurrencyOps.class);
    public ExchangeRateService exchangeRateService = new ExchangeRateImpl();

    private EnumMap<Currency, BigDecimal> init() {
        exchangeRateService.setExchangeRate(DOLLAR, BigDecimal.valueOf(30));
        exchangeRateService.setExchangeRate(EURO, BigDecimal.valueOf(33));
        exchangeRateService.setExchangeRate(HRYVNYA, BigDecimal.valueOf(1));
        return exchangeRateService.getExchangeRates();
    }

    public void buy(UserAccount userAccount, Currency from, Currency to, BigDecimal amount) throws IOException {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        configure();
        CurrencyTransaction transaction = new CurrencyTransaction(userAccount, from, to, amount);
        validate(transaction);
        UserAccountWriter userAccountWriter = new UserAccountWriter();

        performTransaction(transaction, userAccount);
        userAccountWriter.addTransaction(transaction);
        logger.info("Currency operation completed");
    }


    private void performTransaction(CurrencyTransaction transaction, UserAccount userAccount) {
        EnumMap<Currency, BigDecimal> rates = init();
        EnumMap<Currency, BigDecimal> wallet = userAccount.getWallet();
        updateWallet(wallet, rates, transaction);
    }

    private void updateWallet(EnumMap<Currency, BigDecimal> wallet, EnumMap<Currency, BigDecimal> rates, CurrencyTransaction transaction) {
        var from = transaction.getFrom();
        var to = transaction.getTo();
        var amount = transaction.getAmount();

        if (from == HRYVNYA && to == DOLLAR) {
            wallet.put(DOLLAR, wallet.get(DOLLAR).add(amount));
            wallet.put(HRYVNYA, wallet.get(HRYVNYA).subtract(amount.multiply(rates.get(DOLLAR))));
        } else if (from == HRYVNYA && to == EURO) {
            wallet.put(EURO, wallet.get(EURO).add(amount));
            wallet.put(HRYVNYA, wallet.get(HRYVNYA).subtract(amount.multiply(rates.get(EURO))));
        } else if (from == DOLLAR && to == HRYVNYA) {
            wallet.put(HRYVNYA, wallet.get(HRYVNYA).add(amount));
            wallet.put(DOLLAR, wallet.get(DOLLAR).subtract(amount.divide(rates.get(DOLLAR), 2)));
        } else if (from == DOLLAR && to == EURO) {
            wallet.put(EURO, wallet.get(EURO).add(amount));
            wallet.put(DOLLAR, wallet.get(DOLLAR).subtract(
                    amount.multiply(rates.get(EURO)).divide(rates.get(DOLLAR), 2)));
        } else if (from == EURO && to == HRYVNYA) {
            wallet.put(HRYVNYA, wallet.get(HRYVNYA).add(amount));
            wallet.put(EURO, wallet.get(EURO).subtract(amount.divide(rates.get(EURO), 2)));
        } else if (from == EURO && to == DOLLAR) {
            wallet.put(DOLLAR, wallet.get(DOLLAR).add(amount));
            wallet.put(DOLLAR, wallet.get(DOLLAR).subtract(
                    amount.multiply(rates.get(DOLLAR)).divide(rates.get(EURO), 2)));
        }
    }
    private void validate(CurrencyTransaction transaction){
        if (transaction.getFrom() == transaction.getTo())
            throw new SameCurrencyExchangeException("You are not allowed to exchange same currency");
        logger.info("Validation passed");
    }

    private static void configure() {
        Logger logger = Logger.getRootLogger();
        logger.addAppender(new ConsoleAppender(new PatternLayout("%r [%t] %p %c %x - %m%n")));
    }
}
