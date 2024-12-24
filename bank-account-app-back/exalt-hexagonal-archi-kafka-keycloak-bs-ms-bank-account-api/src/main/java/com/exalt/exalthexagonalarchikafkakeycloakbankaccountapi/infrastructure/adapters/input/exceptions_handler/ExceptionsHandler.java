package com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.input.exceptions_handler;

import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.exceptions.*;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.output.domain.dtos.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.Timestamp;
import java.time.Instant;

@ControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorResponse> handler(RuntimeException runtimeException){
        var errorResponse = ErrorResponse.builder()
                .timestamp(Timestamp.from(Instant.now()))
                .build();
        var badRequestCode = 400;
        var notFoundCode = 404;
        switch (runtimeException) {
            case FirstDepositBalanceNotEnoughException exception ->{
                errorResponse.setCode(badRequestCode);
                errorResponse.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
                errorResponse.setMessage(exception.getMessage());
                return ResponseEntity.badRequest().body(errorResponse);
            }
            case CustomerStateException exception ->{
                errorResponse.setCode(badRequestCode);
                errorResponse.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
                errorResponse.setMessage(exception.getMessage());
                return ResponseEntity.badRequest().body(errorResponse);
            }
            case RemoteClientApiUnreachableException exception ->{
                errorResponse.setCode(notFoundCode);
                errorResponse.setStatus(HttpStatus.NOT_FOUND.getReasonPhrase());
                errorResponse.setMessage(exception.getMessage());
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(errorResponse);
            }
            case RemoteCustomerStateUnAllowedException exception ->{
                errorResponse.setCode(badRequestCode);
                errorResponse.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
                errorResponse.setMessage(exception.getMessage());
                return ResponseEntity.badRequest().body(errorResponse);
            }
            case AccountSameStateException exception ->{
                errorResponse.setCode(badRequestCode);
                errorResponse.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
                errorResponse.setMessage(exception.getMessage());
                return ResponseEntity.badRequest().body(errorResponse);
            }
            default -> {
                errorResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
                errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
                errorResponse.setMessage(runtimeException.getMessage());
                return ResponseEntity.internalServerError().body(errorResponse);
            }
        }
    }
}
