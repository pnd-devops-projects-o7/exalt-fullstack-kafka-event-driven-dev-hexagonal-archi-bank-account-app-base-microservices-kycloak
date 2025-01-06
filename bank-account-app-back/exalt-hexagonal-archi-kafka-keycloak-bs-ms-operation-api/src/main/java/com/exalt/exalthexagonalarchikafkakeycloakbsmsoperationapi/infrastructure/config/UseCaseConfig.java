package com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.config;

import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.ports.output.EventProducer;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.ports.output.OperationOutputService;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.ports.output.TransferOutputService;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.usecase.OperationInputServiceImpl;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.usecase.TransferInputServiceImpl;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.feign_clients.services.RemoteAccountService;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.feign_clients.services.RemoteCustomerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Level;
import java.util.logging.Logger;

@Configuration
public class UseCaseConfig {
    private static final Logger LOGGER = Logger.getLogger(UseCaseConfig.class.getName());

    @Bean
    public OperationInputServiceImpl configureOperation(final RemoteAccountService accountService, final RemoteCustomerService customerService,
                                                        final OperationOutputService operationService, final EventProducer eventProducer) {
        LOGGER.log(Level.INFO, "configure InputServiceImpl class to be injected as spring bean");
        return new OperationInputServiceImpl(accountService, customerService, operationService, eventProducer);
    }
    @Bean
    public TransferInputServiceImpl configureTransfer(final RemoteAccountService accountService, final RemoteCustomerService customerService,
                                                      final TransferOutputService transferService, final EventProducer eventProducer){
        return new TransferInputServiceImpl(accountService,customerService,transferService,eventProducer);
    }
}
