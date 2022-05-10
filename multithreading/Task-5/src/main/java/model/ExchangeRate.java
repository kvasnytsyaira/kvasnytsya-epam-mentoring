package model;

import java.math.BigDecimal;
import java.util.EnumMap;

public class ExchangeRate {
    private static final EnumMap<Currency, BigDecimal> rates = new EnumMap<>(Currency.class);

    public ExchangeRate() {
    }

    public static BigDecimal getRate(Currency currency) {
        return rates.get(currency);
    }

    public static void setRate(Currency currency, BigDecimal rate) {
        rates.put(currency, rate);
    }

    public EnumMap<Currency, BigDecimal> getRates() {
        return rates;
    }
}
