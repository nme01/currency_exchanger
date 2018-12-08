package com.jwsolutions.pretiustest.services;

import java.math.BigDecimal;

/**
 * Provides an exchange rate for a given pair of currencies.
 */
public interface ExchangeRatesProvider {
    BigDecimal getExchangeRate(String sourceCurrency, String targetCurrency);
}
