package com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.input.web;

import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.ports.input.InputService;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.output.dtos.OperationRequestDto;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.output.dtos.OperationResponseDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value = "/api/operation")
@RequiredArgsConstructor
public class OperationApiWeb {
    private final InputService inputService;
    private static final Logger LOGGER = LoggerFactory.getLogger(OperationApiWeb.class.getSimpleName());
    @GetMapping(value = "/operations")
    @PreAuthorize("hasAnyRole('CLIENT_USER','CLIENT_ADMIN','CLIENT_OWNER')")
    public ResponseEntity<Collection<OperationResponseDto>> getAllOperations(){
        LOGGER.debug("load all operations");
        return ResponseEntity.ok().body(inputService.getAllOperations());
    }
    @PostMapping(value = "/operations")
    @PreAuthorize("hasRole('CLIENT_ADMIN')")
    public ResponseEntity<OperationResponseDto> createOperation(@RequestBody OperationRequestDto operationRequestDto){
        LOGGER.debug("create operation {} ",operationRequestDto);
        return ResponseEntity.ok().body(inputService.createOperation(operationRequestDto));
    }
}
