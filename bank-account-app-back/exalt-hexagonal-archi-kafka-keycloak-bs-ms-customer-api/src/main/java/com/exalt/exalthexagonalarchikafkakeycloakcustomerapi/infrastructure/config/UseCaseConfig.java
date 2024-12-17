package com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.config;

import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.output.CustomerOutputService;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.output.CustomerProducerEvent;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.usecase.InputServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class UseCaseConfig {
    @Bean
    public InputServiceImpl config (CustomerProducerEvent customerProducerEvent, CustomerOutputService outputService){
        return new InputServiceImpl(customerProducerEvent, outputService);
    }
}