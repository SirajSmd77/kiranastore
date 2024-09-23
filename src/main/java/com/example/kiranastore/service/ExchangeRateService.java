package com.example.kiranastore.service;

import com.example.kiranastore.exception.CurrencyNotFoundException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExchangeRateService {

    private static final Logger log = LoggerFactory.getLogger(ReportService.class);


    private final String EXCHANGE_API_URL = "https://api.fxratesapi.com/latest";  // The API URL

        @Autowired
        private RestTemplate restTemplate;


        @Cacheable(value = "exchangeRates", key = "#currency")
        public Double getExchangeRate(String currency) throws CurrencyNotFoundException {
            // Make the API call using RestTemplate
            ResponseEntity<String> response = restTemplate.getForEntity(EXCHANGE_API_URL, String.class);
            log.info("response is :{}",response.getBody());
            // Parse the JSON response
            JSONObject jsonObject = new JSONObject(response.getBody());

            // Check if the API call was successful
            boolean success = jsonObject.getBoolean("success");
            if (!success) {
                throw new RuntimeException("API call failed");
            }

            // Get the rates object from the response
            JSONObject rates = jsonObject.getJSONObject("rates");
            log.info("rates is :{}",rates);

            // Check if the requested currency is available
            if (!rates.has(currency)) {
                throw new CurrencyNotFoundException("Currency not found: " + currency);
            }

            // Return the exchange rate for the requested currency
            return rates.getDouble(currency);
        }

}
