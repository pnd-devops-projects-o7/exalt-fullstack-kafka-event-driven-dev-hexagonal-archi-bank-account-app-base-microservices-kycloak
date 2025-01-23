package com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.exceptions_handler;

import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.exceptions.AccountNotFoundException;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.exceptions.BankAccountApiBusinessException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GlobalExceptionsHandlerTest {
    GlobalExceptionsHandler globalExceptionsHandler;
    @BeforeEach
    void setUp() {
        globalExceptionsHandler = new GlobalExceptionsHandler();
    }

    @Test
    void handler() {
        RuntimeException runtimeException1 = new AccountNotFoundException("not found");
        var response1 = globalExceptionsHandler.handler(runtimeException1);

        RuntimeException runtimeException2 = new BankAccountApiBusinessException(" any message");
        var response2 = globalExceptionsHandler.handler(runtimeException2);
        Assertions.assertAll("**",()->{
            Assertions.assertNotNull(response1);
            Assertions.assertNotNull(response2);
        });
    }
}