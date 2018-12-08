package com.jwsolutions.pretiustest.services.impl;

import com.jwsolutions.pretiustest.services.CurrencyExchanger;
import com.jwsolutions.pretiustest.services.ExchangeRatesProvider;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CurrencyExchangerImpl implements CurrencyExchanger {

    private ExchangeRatesProvider exchangeRatesProvider;

    public CurrencyExchangerImpl(ExchangeRatesProvider exchangeRatesProvider) {
        this.exchangeRatesProvider = exchangeRatesProvider;
    }

    @Override
    public BigDecimal exchangeMoney(BigDecimal amount, String sourceCurrency, String targetCurrency) {
        if (sourceCurrency.equalsIgnoreCase(targetCurrency)) {
            return amount;
        }

        BigDecimal rate = exchangeRatesProvider.getExchangeRate(sourceCurrency, targetCurrency);
        BigDecimal targetAmount = amount.multiply(rate);

        return targetAmount;
    }
}
