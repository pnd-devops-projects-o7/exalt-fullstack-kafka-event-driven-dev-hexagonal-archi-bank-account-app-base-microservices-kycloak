package com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.usecase;

import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.ports.output.EventProducer;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.ports.output.OutputService;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.feign_client.services.RemoteClientService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class InputServiceImplTest {
    @Mock
    private OutputService outputServiceMock;
    @Mock
    private RemoteClientService remoteClientServiceMock;
    @Mock
    private EventProducer eventProducerMock;
    @Test
    void createAccount() {
        Assertions.assertAll("",()->{
        });
    }
 }