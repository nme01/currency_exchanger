package com.jwsolutions.pretiustest.services.nbp.downloader;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Downloads mid-day exchange rates from the NBP (National Polish Bank) API.
 */
public class NbpExchangeRateDownloader {
    private static final String BASE_URI = "http://api.nbp.pl/api/exchangerates/tables/";
    private final RestTemplate restTemplate = new RestTemplate();

    public Map<String, BigDecimal> downloadExchangeRatesForPLN() {
        Collection<NbpRate> exchangableCurrenciesRates = downloadRates("A");
        Collection<NbpRate> nonexchangableCurrenciesRates = downloadRates("B");

        Map<String, BigDecimal> exchangeRatesForPLN =
            Stream.concat(exchangableCurrenciesRates.stream(), nonexchangableCurrenciesRates.stream())
                .collect(Collectors.toMap(NbpRate::getCode, nbpRate -> new BigDecimal(nbpRate.getMid())));

        return exchangeRatesForPLN;
    }

    private Collection<NbpRate> downloadRates(String tableSymbol) {
        URI tableUri = URI.create(BASE_URI + tableSymbol);
        RequestEntity<Void> rates = RequestEntity
            .get(tableUri)
            .accept(MediaType.APPLICATION_JSON)
            .build();

        ResponseEntity<? extends Collection<NbpResponse>> responseEntity = restTemplate.exchange(
            rates,
            new ParameterizedTypeReference<Collection<NbpResponse>>(){}
        );

        NbpResponse response = responseEntity.getBody().stream().findAny().get();
        return response.getRates();
    }
}
