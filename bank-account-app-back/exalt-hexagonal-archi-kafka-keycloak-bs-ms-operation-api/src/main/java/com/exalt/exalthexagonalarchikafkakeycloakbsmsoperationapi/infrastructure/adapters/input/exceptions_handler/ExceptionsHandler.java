package com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.input.exceptions_handler;

import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.exceptions.*;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.output.dtos.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.Timestamp;
import java.time.Instant;

@ControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorResponse> handle(RuntimeException runtimeException){
        var badRequestCode = 400;
        ErrorResponse badRequesrErrorResponse = ErrorResponse.builder()
                .code(badRequestCode)
                .status(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .timestamp(Timestamp.from(Instant.now()))
                .build();

        switch (runtimeException){
            case InputFieldsInvalidException exception ->{
                badRequesrErrorResponse.setMessage(exception.getMessage());
                return ResponseEntity.badRequest().body(badRequesrErrorResponse);
            }
            case TransactionAmountInvalidException exception ->{
                badRequesrErrorResponse.setMessage(exception.getMessage());
                return ResponseEntity.badRequest().body(badRequesrErrorResponse);
            }
            case OperationTypeInvalidException exception ->{
                badRequesrErrorResponse.setMessage(exception.getMessage());
                return ResponseEntity.badRequest().body(badRequesrErrorResponse);
            }
            case RemoteAccountStateUnAllowedException exception ->{
                badRequesrErrorResponse.setMessage(exception.getMessage());
                return ResponseEntity.badRequest().body(badRequesrErrorResponse);
            }
            case RemoteAccountTypeUnAllowedException exception ->{
                badRequesrErrorResponse.setMessage(exception.getMessage());
                return ResponseEntity.badRequest().body(badRequesrErrorResponse);
            }
            case RemoteAccountBalanceInsufficientException exception ->{
                badRequesrErrorResponse.setMessage(exception.getMessage());
                return ResponseEntity.badRequest().body(badRequesrErrorResponse);
            }
            default -> {
                ErrorResponse errorResponse = ErrorResponse.builder()
                        .code(500)
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                        .message(runtimeException.getMessage())
                        .timestamp(Timestamp.from(Instant.now()))
                        .build();
                return ResponseEntity.internalServerError().body(errorResponse);
            }
        }
    }
}
