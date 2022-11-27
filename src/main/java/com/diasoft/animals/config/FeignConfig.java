package com.diasoft.animals.config;

import feign.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    @Value("${feign.log.level}")
    private String logLevel;

    private final String NONE = "NONE";
    private final String BASIC = "BASIC";
    private final String HEADERS = "HEADERS";

    @Bean
    Logger.Level feignCustomLoggerLevel() {
        Logger.Level level = switch (logLevel) {
            case (NONE) -> Logger.Level.NONE;
            case (BASIC) -> Logger.Level.BASIC;
            case (HEADERS) -> Logger.Level.HEADERS;
            default -> Logger.Level.FULL;
        };
        return level;
    }
}