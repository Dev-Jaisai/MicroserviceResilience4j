package com.resilience4J.ratelimiter.service;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RateLimiterService {


    public String accessFirstApi() {
        log.info("Accessing First API");
        return "Accessed First API successfully!";
    }


    /**
     * Retry mechanism for the second API.
     * This implementation retries the operation up to 3 times in case of failure.
     *
     * @return A message indicating whether access was successful.
     */
    @Retry(name = "secondApiRetry", fallbackMethod = "retryFallback")
    public String accessSecondApi() {
        log.info("Accessing Second API");
        // Simulate an error to trigger retry
        if (Math.random() > 0.7) {
            throw new RuntimeException("Simulated failure for retry");
        }
        return "Accessed Second API successfully!";
    }

    public String retryFallback(Throwable t) {
        log.warn("Retry attempts exhausted: {}", t.getMessage());
        return "Retry attempts exhausted. Please try again later.";
    }
}
