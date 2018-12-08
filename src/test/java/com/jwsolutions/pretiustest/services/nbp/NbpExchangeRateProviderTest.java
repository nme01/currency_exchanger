package com.jwsolutions.pretiustest.services.nbp;

import com.jwsolutions.pretiustest.services.nbp.downloader.NbpExchangeRateDownloader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class NbpExchangeRateProviderTest  {
    private NbpExchangeRateProvider exchangeRateProvider;

    @BeforeEach
    public void setUpNbpExchangeRateDownloader() {
        NbpExchangeRateDownloader dataDownloader = Mockito.mock(NbpExchangeRateDownloader.class);
        when(dataDownloader.downloadExchangeRatesForPLN()).thenReturn(
            new HashMap<String, BigDecimal>() {{
                put("CHF", new BigDecimal("3.7900"));
                put("PLN", new BigDecimal("1.0000"));
            }}
        );

        this.exchangeRateProvider = new NbpExchangeRateProvider(dataDownloader);
    }

    @Test
    @DisplayName("1 PLN should be 0.2639 CHF")
    public void plnToChfRate() {
        BigDecimal actualRate = exchangeRateProvider.getExchangeRate("PLN", "CHF");
        BigDecimal expectedRate = new BigDecimal("0.2639");
        assertEquals(expectedRate, actualRate);
    }

    @Test
    @DisplayName("1 PLN should be 1.0000 PLN")
    public void plnToPlnRate() {
        BigDecimal actualRate = exchangeRateProvider.getExchangeRate("PLN", "PLN");
        BigDecimal expectedRate = new BigDecimal("1.0000");
        assertEquals(expectedRate, actualRate);
    }

    @Test
    @DisplayName("1 CHF should be 3.7900 PLN")
    public void chfToChfRate() {
        BigDecimal actualRate = exchangeRateProvider.getExchangeRate("CHF", "PLN");
        BigDecimal expectedRate = new BigDecimal("3.7900");
        assertEquals(expectedRate, actualRate);
    }
}
