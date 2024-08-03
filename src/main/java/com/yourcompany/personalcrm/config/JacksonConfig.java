package com.yourcompany.personalcrm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.cfg.ConstructorDetector;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class JacksonConfig {

    @Bean
    public Jackson2ObjectMapperBuilder objectMapper() {
        log.info("Configuring Jackson");
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        //builder.modules(new JavaTimeModule());
        builder.postConfigurer(om -> om
            .setConstructorDetector(ConstructorDetector.USE_PROPERTIES_BASED)
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false));
        return builder;
    }
}