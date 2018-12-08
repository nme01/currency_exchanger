package com.jwsolutions.pretiustest.services.nbp;

import com.jwsolutions.pretiustest.services.ExchangeRatesProvider;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class NbpExchangeRateProvider implements ExchangeRatesProvider {

    private Map<String, BigDecimal> exchangeRates;

    public NbpExchangeRateProvider() {
        downloadExchangeRates();
    }

    @Override
    public BigDecimal getExchangeRate(String sourceCurrency, String destinationCurrency) {
        return new BigDecimal("2.0");
    }

    private void downloadExchangeRates() {
        // TODO
    }

}
