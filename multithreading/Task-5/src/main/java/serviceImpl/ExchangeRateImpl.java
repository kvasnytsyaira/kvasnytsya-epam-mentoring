package serviceImpl;

import model.Currency;
import model.ExchangeRate;
import service.ExchangeRateService;

import java.math.BigDecimal;
import java.util.EnumMap;

public class ExchangeRateImpl implements ExchangeRateService {
    @Override
    public void setExchangeRate(Currency currency, BigDecimal rate) {
        ExchangeRate exchangeRate = new ExchangeRate();
    }

    @Override
    public EnumMap<Currency, BigDecimal> getExchangeRates() {
        return null;
    }
}
