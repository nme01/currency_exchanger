package com.jwsolutions.pretiustest.controllers.currency_exchange;

import com.jwsolutions.pretiustest.services.CurrencyExchanger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * Rest controller providing endpoints for exchanging currencies using NBP currency data.
 */
@RestController
public class CurrencyExchangeController {

    private CurrencyExchanger currencyExchanger;

    public CurrencyExchangeController(CurrencyExchanger currencyExchanger) {
        this.currencyExchanger = currencyExchanger;
    }

    @GetMapping("/exchange")
    public ResponseEntity<BigDecimal> exchange(@RequestParam("amount") String amount,
                                               @RequestParam("sourceCurrency") String sourceCurrency,
                                               @RequestParam("targetCurrency") String targetCurrency) {
        BigDecimal sourceAmount = new BigDecimal(amount);
        BigDecimal targetAmount = currencyExchanger.exchangeMoney(sourceAmount, sourceCurrency, targetCurrency);

        return new ResponseEntity<>(targetAmount, HttpStatus.OK);
    }
}
