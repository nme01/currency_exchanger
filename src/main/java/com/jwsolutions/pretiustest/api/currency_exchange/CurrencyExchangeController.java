package com.jwsolutions.pretiustest.api.currency_exchange;

import com.jwsolutions.pretiustest.services.CurrencyExchangeException;
import com.jwsolutions.pretiustest.services.CurrencyExchanger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Rest controller providing endpoints for exchanging currencies using NBP currency data.
 */
@RestController
public class CurrencyExchangeController {
    @Value("${currency-exchanger.scale}")
    private int scale;

    private CurrencyExchanger currencyExchanger;

    public CurrencyExchangeController(CurrencyExchanger currencyExchanger) {
        this.currencyExchanger = currencyExchanger;
    }

    @GetMapping("/exchange")
    public ResponseEntity<BigDecimal> exchange(@RequestParam("amount") String amount,
                                               @RequestParam("sourceCurrency") String sourceCurrency,
                                               @RequestParam("targetCurrency") String targetCurrency) {
        BigDecimal sourceAmount = new BigDecimal(amount);
        BigDecimal targetAmount;
        try {
            targetAmount = currencyExchanger.exchangeMoney(sourceAmount, sourceCurrency, targetCurrency);
        } catch (CurrencyExchangeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (targetAmount != null) {
            targetAmount = targetAmount.setScale(scale, RoundingMode.HALF_UP);
            return new ResponseEntity<>(targetAmount, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
