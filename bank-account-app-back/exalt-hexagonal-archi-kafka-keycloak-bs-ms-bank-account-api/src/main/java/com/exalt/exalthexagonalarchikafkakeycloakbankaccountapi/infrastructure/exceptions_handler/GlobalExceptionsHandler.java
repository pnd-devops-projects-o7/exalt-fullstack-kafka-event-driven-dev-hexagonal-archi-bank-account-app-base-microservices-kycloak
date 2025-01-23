package com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.exceptions_handler;

import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.exceptions.*;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.output.domain.dtos.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.Timestamp;
import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionsHandler {
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorResponse> handler(RuntimeException runtimeException){
        var errorResponse = ErrorResponse.builder()
                .timestamp(Timestamp.from(Instant.now()))
                .build();
        var badRequestCodeError = ErrorResponse.builder()
                .code(400)
                .timestamp(Timestamp.from(Instant.now()))
                .status(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .build();
        if(runtimeException instanceof BankAccountApiBusinessException exception){
            badRequestCodeError.setMessage(exception.getMessage());
            return ResponseEntity.badRequest().body(badRequestCodeError);
        }
        else if(runtimeException instanceof AccountNotFoundException exception){
            errorResponse.setMessage(exception.getMessage());
            return ResponseEntity.ofNullable(errorResponse);
        }
        errorResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        errorResponse.setMessage(runtimeException.getMessage());
        return ResponseEntity.internalServerError().body(errorResponse);
    }
}
