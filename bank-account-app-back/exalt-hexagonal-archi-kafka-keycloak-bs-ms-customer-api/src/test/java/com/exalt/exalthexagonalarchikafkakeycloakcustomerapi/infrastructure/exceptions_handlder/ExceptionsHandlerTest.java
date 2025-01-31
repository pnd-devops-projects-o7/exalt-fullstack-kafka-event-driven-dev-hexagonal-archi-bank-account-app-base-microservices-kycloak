package com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.exceptions_handlder;

import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.exceptions.CustomerApiBusinessException;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.exceptions.CustomerNotFoundException;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.security.access.AccessDeniedException;

import static org.junit.jupiter.api.Assertions.*;

class ExceptionsHandlerTest {
    private final ExceptionsHandler exceptionsHandler = new ExceptionsHandler();

    @Test
    void handlerBusinessExceptions() {
        RuntimeException runtimeException = new CustomerApiBusinessException("any exception message");
        var responseError1 = exceptionsHandler.handlerBusinessExceptions(runtimeException);
        RuntimeException exception = new CustomerNotFoundException("CustomerNotFoundException");
        var responseError2 = exceptionsHandler.handlerBusinessExceptions(exception);

        RuntimeException exception2 = new AccessDeniedException("any exception message2");
        var responseError3 = exceptionsHandler.handlerBusinessExceptions(exception2);

        Assertions.assertAll("*",()->{
            Assertions.assertNotNull(responseError1);
            Assertions.assertNotNull(responseError2);
            Assertions.assertNotNull(responseError3);
        });
    }
}