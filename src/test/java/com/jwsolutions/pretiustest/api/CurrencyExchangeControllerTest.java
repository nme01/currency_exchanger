package com.jwsolutions.pretiustest.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static com.jwsolutions.pretiustest.api.currency_exchange.CurrencyExchangeController.EXCHANGE_URI;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class CurrencyExchangeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Should convert 1 CHF to 3.7900 PLN")
    public void convertChfToPln() throws Exception {
        String uri = EXCHANGE_URI + "?amount=1.0000&sourceCurrency=CHF&targetCurrency=PLN";

        this.mockMvc.perform(get(uri))
            .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should respond with a 400 Bad Request error")
    public void convertUsingInvalidCurrency() throws Exception {
        String uri = EXCHANGE_URI + "?amount=1.0000&sourceCurrency=DDD&targetCurrency=PLN";

        this.mockMvc.perform(get(uri))
                .andExpect(status().isBadRequest());
    }
}
