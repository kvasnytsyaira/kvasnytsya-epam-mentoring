package model;

import java.math.BigDecimal;
import java.util.EnumMap;

public class ExchangeRate {
    private final EnumMap<Currency, BigDecimal> rates;

    public ExchangeRate() {
        rates = new EnumMap<Currency, BigDecimal>(Currency.class);
    }

    public BigDecimal getRate(Currency currency) {
        return rates.get(currency);
    }

    public void setRate(Currency currency, BigDecimal rate) {
        rates.put(currency, rate);
    }
}
