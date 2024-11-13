package com.resilience4J.ratelimiter.service;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RateLimiterService {
    public String accessFirstApi() {
        log.info("Accessing First API");
        return "Accessed First API successfully!";
    }

}
