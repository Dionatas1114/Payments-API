package com.api.payments.config;

import com.github.f4b6a3.uuid.UuidCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;
import java.util.function.Supplier;

@Configuration
public class JpaConfig {

    @Bean
    public Supplier<UUID> uuidV7Supplier() {
        return UuidCreator::getTimeOrdered;
    }
}