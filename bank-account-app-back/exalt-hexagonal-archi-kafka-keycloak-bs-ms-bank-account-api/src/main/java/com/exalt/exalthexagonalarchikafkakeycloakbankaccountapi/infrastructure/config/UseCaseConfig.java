package com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.config;

import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.ports.output.EventProducer;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.ports.output.OutputService;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.usecase.InputServiceImpl;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.usecase.ScheduledJobImpl;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.feign_client.services.RemoteClientService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Level;
import java.util.logging.Logger;

@Configuration
public class UseCaseConfig {
    private static final Logger LOGGER = Logger.getLogger(UseCaseConfig.class.getName());
    @Bean
    public InputServiceImpl config1(final OutputService outputService, final RemoteClientService remoteClientService,
                                               EventProducer eventProducer) {
        LOGGER.log(Level.INFO,"configure use case to be injected as spring bean");
        return new InputServiceImpl(outputService, remoteClientService, eventProducer);
    }
    @Bean
    public ScheduledJobImpl config2(final OutputService outputService, final EventProducer eventProducer){
        return new ScheduledJobImpl(outputService,eventProducer);
    }
}
