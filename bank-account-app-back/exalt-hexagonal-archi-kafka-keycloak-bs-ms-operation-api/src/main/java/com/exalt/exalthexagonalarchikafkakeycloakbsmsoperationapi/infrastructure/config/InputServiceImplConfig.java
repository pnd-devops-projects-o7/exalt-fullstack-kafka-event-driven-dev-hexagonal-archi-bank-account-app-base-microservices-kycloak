package com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.config;

import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.ports.output.EventProducer;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.ports.output.OutputService;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.usecase.InputServiceImpl;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.input.feign_clients.services.RemoteAccountService;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.input.feign_clients.services.RemoteCustomerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Level;
import java.util.logging.Logger;

@Configuration
public class InputServiceImplConfig {
    private static final Logger LOGGER = Logger.getLogger(InputServiceImplConfig.class.getName());
    @Bean
    public InputServiceImpl configure(RemoteAccountService remoteAccountService, RemoteCustomerService remoteCustomerService,
                                      OutputService outputService, EventProducer operationEventProducer){
        LOGGER.log(Level.INFO,"configure InputServiceImpl class to be injected as spring bean");
        return new InputServiceImpl(remoteAccountService,remoteCustomerService,outputService,operationEventProducer);
    }
}
