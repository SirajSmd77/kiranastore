package com.example.kiranastore.service;

import com.example.kiranastore.exception.RateLimitException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RateLimitAspect {

        private final RateLimiterService rateLimiterService;

        public RateLimitAspect(RateLimiterService rateLimiterService) {
            this.rateLimiterService = rateLimiterService;
        }

        @Around("@annotation(rateLimited)")
        public Object rateLimit(ProceedingJoinPoint joinPoint, RateLimited rateLimited) throws Throwable {
            String key = joinPoint.getSignature().toShortString(); // Unique key for method
            boolean allowed = rateLimiterService.isRequestAllowed(key);

            if (!allowed) {
                throw new RateLimitException("Rate limit exceeded. Please try again later.");
            }

            return joinPoint.proceed();
        }
    }
