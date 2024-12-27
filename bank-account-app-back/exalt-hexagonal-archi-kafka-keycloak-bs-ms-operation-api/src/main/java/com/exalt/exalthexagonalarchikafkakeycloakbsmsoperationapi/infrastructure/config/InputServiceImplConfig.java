package com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.config;

import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.ports.output.EventProducer;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.ports.output.OperationOutputService;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.ports.output.TransferOutputService;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.usecase.OperationInputServiceImpl;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.usecase.TransferInputServiceImpl;
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
    public OperationInputServiceImpl configureOperation(RemoteAccountService accountService, RemoteCustomerService customerService,
                                               OperationOutputService operationService, EventProducer eventProducer) {
        LOGGER.log(Level.INFO, "configure InputServiceImpl class to be injected as spring bean");
        return new OperationInputServiceImpl(accountService, customerService, operationService, eventProducer);
    }
    @Bean
    public TransferInputServiceImpl configureTransfer(RemoteAccountService accountService, RemoteCustomerService customerService,
                                              TransferOutputService transferService, EventProducer eventProducer){
        return new TransferInputServiceImpl(accountService,customerService,transferService,eventProducer);
    }
}
