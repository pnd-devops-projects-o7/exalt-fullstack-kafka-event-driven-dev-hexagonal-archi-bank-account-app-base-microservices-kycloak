package com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.exceptions_handlder;

import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.exceptions.*;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.adapters.output.domain.dtos.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.Timestamp;
import java.time.Instant;

@ControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorResponseDto> handlerBusinessExceptions(RuntimeException runtimeException){
        final int badRequestCode=400;
        final String badRequestStatus = HttpStatus.BAD_REQUEST.getReasonPhrase();
        final Timestamp createdAt = Timestamp.from(Instant.now());
        ErrorResponseDto errorResponseDto = ErrorResponseDto.builder()
                .timestamp(createdAt)
                .build();

        switch (runtimeException) {
            case CustomerApiBusinessException businessException ->{
                errorResponseDto.setCode(badRequestCode);
                errorResponseDto.setStatus(badRequestStatus);
                errorResponseDto.setMessage(businessException.getMessage());
                return ResponseEntity.badRequest().body(errorResponseDto);
            }
            case CustomerNotFoundException businessException -> {
                errorResponseDto.setCode(404);
                errorResponseDto.setStatus(HttpStatus.NOT_FOUND.getReasonPhrase());
                errorResponseDto.setMessage(businessException.getMessage());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseDto);
            }
            case AccessDeniedException springSecurityException ->{
                errorResponseDto.setCode(403);
                errorResponseDto.setStatus(HttpStatus.FORBIDDEN.getReasonPhrase());
                errorResponseDto.setMessage(springSecurityException.getMessage());
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponseDto);
            }
            default -> {
                errorResponseDto.setCode(500);
                errorResponseDto.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
                errorResponseDto.setMessage(runtimeException.getMessage());
                return ResponseEntity.internalServerError().body(errorResponseDto);
            }
        }
    }
}