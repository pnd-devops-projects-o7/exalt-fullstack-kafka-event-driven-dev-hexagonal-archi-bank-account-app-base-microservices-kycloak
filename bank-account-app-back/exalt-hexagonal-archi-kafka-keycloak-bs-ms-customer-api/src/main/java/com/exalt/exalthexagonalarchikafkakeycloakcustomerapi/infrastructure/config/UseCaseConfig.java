package com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.config;

import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.output.OutputService;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.output.EventProducer;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.usecase.InputServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class UseCaseConfig {
    @Bean
    public InputServiceImpl config (final EventProducer eventProducer, final OutputService outputService){
        return new InputServiceImpl(eventProducer, outputService);
    }
}