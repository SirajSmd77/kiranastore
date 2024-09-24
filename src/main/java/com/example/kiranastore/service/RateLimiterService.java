package com.example.kiranastore.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class RateLimiterService {

    private final int transactionLimit;
    private final int transactionPeriod;
    private final RedisTemplate<String, String> redisTemplate;

    // Constructor-based dependency injection for RedisTemplate and properties
    public RateLimiterService(
            RedisTemplate<String, String> redisTemplate,
            @Value("${rate-limiting.transactions.limit}") int transactionLimit,
            @Value("${rate-limiting.transactions.period}") int transactionPeriod) {
        this.redisTemplate = redisTemplate;
        this.transactionLimit = transactionLimit;
        this.transactionPeriod = transactionPeriod;
    }

    // Method to check if the request is allowed based on rate-limiting logic
    public boolean isRequestAllowed(String key) {
        String redisKey = "rate_limit:" + key;

        // Increment the request count in Redis
        Long requestCount = redisTemplate.opsForValue().increment(redisKey, 1);

        if (requestCount == 1) {
            // Set the expiration time when the key is created
            redisTemplate.expire(redisKey, Duration.ofSeconds(transactionPeriod));
        }

        // If the request count exceeds the limit, return false
        return requestCount <= transactionLimit;
    }
}
