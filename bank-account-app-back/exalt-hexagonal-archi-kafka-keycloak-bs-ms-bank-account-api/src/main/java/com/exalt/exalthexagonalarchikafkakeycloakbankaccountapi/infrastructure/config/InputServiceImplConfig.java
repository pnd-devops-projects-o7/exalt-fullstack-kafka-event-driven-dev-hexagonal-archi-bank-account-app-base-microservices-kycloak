package com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.config;

import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.ports.output.EventProducer;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.ports.output.OutputService;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.usecase.InputServiceImpl;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.input.feign_client.services.RemoteClientService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Level;
import java.util.logging.Logger;

@Configuration
public class InputServiceImplConfig {
    private static final Logger LOGGER = Logger.getLogger(InputServiceImplConfig.class.getName());
    @Bean
    public InputServiceImpl configInputService(OutputService outputService, RemoteClientService remoteClientService,
                                               EventProducer eventProducer) {
        LOGGER.log(Level.INFO,"configure use case to be injected as spring bean");
        return new InputServiceImpl(outputService, remoteClientService, eventProducer);
    }
}
