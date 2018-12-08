package com.jwsolutions.pretiustest.services.impl;

import com.jwsolutions.pretiustest.services.ExchangeRatesProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class CurrencyExchangerImplTest {

    private CurrencyExchangerImpl currencyExchanger;

    @BeforeEach
    public void setUpExchangeRateProvider() {
        ExchangeRatesProvider ratesProvider = Mockito.mock(ExchangeRatesProvider.class);
        when(ratesProvider.getExchangeRate("CHF", "PLN")).thenReturn(new BigDecimal("3.7900"));
        when(ratesProvider.getExchangeRate("PLN", "CHF")).thenReturn(new BigDecimal("0.2639"));

        currencyExchanger = new CurrencyExchangerImpl(ratesProvider);
    }

    @Test
    @DisplayName("For same currency original amount should be returned")
    public void exchangeWithTheSameSourceAndTargetCurrency() {
        BigDecimal actualResult = currencyExchanger.exchangeMoney(new BigDecimal("100.0"), "PLN", "PLN");
        BigDecimal expectedResult = new BigDecimal("100.0");

        assertEquals(expectedResult, actualResult);

        actualResult = currencyExchanger.exchangeMoney(new BigDecimal("100.0"), "CHF", "CHF");
        expectedResult = new BigDecimal("100.0");

        assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("100 CHF should be equal to 379 PLN")
    public void exchangeChfToPln() {
        BigDecimal actualResult = currencyExchanger.exchangeMoney(new BigDecimal("100.0"), "CHF", "PLN");
        BigDecimal expectedResult = new BigDecimal("379.0000");

        assertEquals(0, expectedResult.compareTo(actualResult));  // using compareTo to ignore the differences in scale
    }

    @Test
    @DisplayName("100 PLN should be equal to 26.39 PLN")
    public void exchangePlnToChf() {
        BigDecimal actualResult = currencyExchanger.exchangeMoney(new BigDecimal("100.0"), "PLN", "CHF");
        BigDecimal expectedResult = new BigDecimal("26.39");

        assertEquals(0, expectedResult.compareTo(actualResult));  // using compareTo to ignore the differences in scale
    }
}
