package com.resilience4J.ratelimiter.controller;

import com.resilience4J.ratelimiter.service.RateLimiterService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class RateLimiterController {


    private final RateLimiterService rateLimiterService;

    public RateLimiterController(RateLimiterService rateLimiterService) {
        this.rateLimiterService = rateLimiterService;
    }

    /**
     * Endpoint to access the first API.
     * This API is rate-limited using Resilience4j.
     * If the rate limit is exceeded, the fallback method will be triggered.
     *
     * @return A message indicating whether access was successful or rate limit was exceeded.
     */
    @GetMapping("/api/first")
    @RateLimiter(name = "firstApiLimiter", fallbackMethod = "rateLimiterFallback")
    public String accessFirstApi() {
        log.info("Received request to access First API");
        return rateLimiterService.accessFirstApi();
    }

    /**
     * Fallback method for the rate limiter.
     * This method is called when the rate limit for the first API is exceeded.
     *
     * @param t The throwable that caused the fallback to be triggered.
     * @return A message indicating that the rate limit has been exceeded.
     */
    public String rateLimiterFallback(Throwable t) {
        log.warn("Rate limit exceeded: {}", t.getMessage());
        return "Rate limit exceeded. Please try again later.";
    }
}