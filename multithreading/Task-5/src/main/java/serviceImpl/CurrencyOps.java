package serviceImpl;

import model.Currency;
import model.CurrencyTransaction;
import model.UserAccount;
import service.ExchangeRateService;
import utilities.UserAccountWriter;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.EnumMap;

public class CurrencyOps {
    public ExchangeRateService exchangeRateService;

    public void init() {
        ExchangeRateService exchangeRateService = new ExchangeRateImpl();
        exchangeRateService.setExchangeRate(Currency.DOLLAR, BigDecimal.valueOf(30));
        exchangeRateService.setExchangeRate(Currency.EURO, BigDecimal.valueOf(33));
        exchangeRateService.setExchangeRate(Currency.HRYVNYA, BigDecimal.valueOf(1));
    }

    public void buy(UserAccount userAccount, Currency from, Currency to, BigDecimal amount) throws IOException {
        CurrencyTransaction transaction = new CurrencyTransaction(userAccount, from, to, amount);
        UserAccountWriter userAccountWriter = new UserAccountWriter();

        performTransaction(transaction, userAccount);
        userAccountWriter.addTransaction(transaction);
    }


    private void performTransaction(CurrencyTransaction transaction, UserAccount userAccount) {
        EnumMap<Currency, BigDecimal> wallet = userAccount.getWallet();
        if (transaction.getFrom() == Currency.DOLLAR) {
            wallet.put(Currency.DOLLAR, wallet.get(Currency.DOLLAR).subtract(transaction.getAmount()));
            if (transaction.getTo() == Currency.EURO) {
                wallet.put(Currency.EURO, wallet.get(Currency.EURO).add(transaction.getAmount()));
            } else if (transaction.getTo() == Currency.HRYVNYA) {
                wallet.put(Currency.HRYVNYA, wallet.get(Currency.HRYVNYA).add(transaction.getAmount()));
                System.out.println("put success");
            }
        } else if (transaction.getFrom() == Currency.EURO) {
            wallet.put(Currency.EURO, wallet.get(Currency.EURO).subtract(transaction.getAmount()));
            if (transaction.getTo() == Currency.DOLLAR) {
                wallet.put(Currency.DOLLAR, wallet.get(Currency.DOLLAR).add(transaction.getAmount()));
            } else if (transaction.getTo() == Currency.HRYVNYA) {
                wallet.put(Currency.HRYVNYA, wallet.get(Currency.HRYVNYA).add(transaction.getAmount()));
            }
        } else if (transaction.getFrom() == Currency.HRYVNYA) {
            wallet.put(Currency.HRYVNYA, wallet.get(Currency.HRYVNYA).subtract(transaction.getAmount()));
            if (transaction.getTo() == Currency.DOLLAR) {
                wallet.put(Currency.DOLLAR, wallet.get(Currency.DOLLAR).add(transaction.getAmount()));
            } else if (transaction.getTo() == Currency.EURO) {
                wallet.put(Currency.EURO, wallet.get(Currency.EURO).add(transaction.getAmount()));
            }
        }
    }

}
