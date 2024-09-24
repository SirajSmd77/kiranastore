package com.example.kiranastore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.google.common.util.concurrent.RateLimiter;

@Configuration
public class RateLimiterConfig {

        @Bean
        public RateLimiter rateLimiter() {
            return RateLimiter.create(10.0 / 60.0); // 10 requests per minute
        }
    }

