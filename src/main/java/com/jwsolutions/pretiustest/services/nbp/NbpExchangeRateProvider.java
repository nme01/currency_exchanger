package com.jwsolutions.pretiustest.services.nbp;

import com.jwsolutions.pretiustest.services.ExchangeRatesProvider;
import com.jwsolutions.pretiustest.services.nbp.downloader.NbpExchangeRateDownloader;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

@Service
public class NbpExchangeRateProvider implements ExchangeRatesProvider {

    private Map<String, BigDecimal> exchangeRates;
    private NbpExchangeRateDownloader dataDownloader;

    public NbpExchangeRateProvider() {
        this.dataDownloader = new NbpExchangeRateDownloader();
        downloadExchangeRates();
    }

    @Override
    public BigDecimal getExchangeRate(String sourceCurrency, String targetCurrency) {
        BigDecimal sourceCurrencyRate = exchangeRates.get(sourceCurrency);
        BigDecimal destinationCurrencyRate = exchangeRates.get(targetCurrency);

        BigDecimal exchangeRate = sourceCurrencyRate.divide(destinationCurrencyRate, RoundingMode.HALF_UP);
        return exchangeRate;
    }

    @Scheduled(cron = "* 20 12 ? * MON-FRI")  // just after NBP updates the mid day exchange rates (12:20 working days)
    private void downloadExchangeRates() {
        this.exchangeRates = dataDownloader.downloadExchangeRatesForPLN();
    }

}
