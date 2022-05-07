package service;

import model.Currency;

import java.math.BigDecimal;
import java.util.EnumMap;

public interface ExchangeRateService {
    void setExchangeRate(Currency currency, BigDecimal rate);
    EnumMap<Currency, BigDecimal> getExchangeRates();
}
