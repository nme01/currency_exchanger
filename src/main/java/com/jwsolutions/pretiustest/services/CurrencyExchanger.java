package com.jwsolutions.pretiustest.services;

import java.math.BigDecimal;

public interface CurrencyExchanger {
    BigDecimal exchangeMoney(BigDecimal amount, String sourceCurrency, String targetCurrency);
}
