package com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.input.web;

import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.ports.input.OperationInputService;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.ports.input.TransferInputService;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.output.dtos.OperationRequestDto;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.output.dtos.OperationResponseDto;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.output.dtos.TransferRequestDto;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.output.dtos.TransferResponseDto;
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
    private final OperationInputService operationInputService;
    private final TransferInputService transferInputService;
    private static final Logger LOGGER = LoggerFactory.getLogger(OperationApiWeb.class.getSimpleName());
    @GetMapping(value = "/operations")
    @PreAuthorize("hasAnyRole('CLIENT_USER','CLIENT_ADMIN','CLIENT_OWNER')")
    public ResponseEntity<Collection<OperationResponseDto>> getAllOperations(){
        LOGGER.debug("load all operations");
        return ResponseEntity.ok().body(operationInputService.getAllOperations());
    }
    @PostMapping(value = "/operations")
    @PreAuthorize("hasRole('CLIENT_ADMIN')")
    public ResponseEntity<OperationResponseDto> createOperation(@RequestBody OperationRequestDto operationRequestDto){
        LOGGER.debug("create an operation {} ",operationRequestDto);
        return ResponseEntity.ok().body(operationInputService.createOperation(operationRequestDto));
    }
    @GetMapping(value = "/operations/{operationId}")
    @PreAuthorize("hasAnyRole('CLIENT_OWNER','CLIENT_ADMIN')")
    public ResponseEntity<OperationResponseDto> getOperationById(@PathVariable(value = "operationId") String operationId){
        LOGGER.debug("get operation by id {}",operationId);
        return ResponseEntity.ok().body(operationInputService.getOperationById(operationId));
    }
    @PostMapping(value = "/transfers")
    public ResponseEntity<TransferResponseDto> createTransfer(@RequestBody TransferRequestDto transferRequestDto){
        LOGGER.debug("create a transfer {} ",transferRequestDto);
        return ResponseEntity.ok().body(transferInputService.createTransfer(transferRequestDto));
    }
    @GetMapping(value = "/transfers")
    public ResponseEntity<Collection<TransferResponseDto>> getAllTransfers(){
        LOGGER.debug("load all transfers operations");
        return ResponseEntity.ok().body(transferInputService.getAllTransfers());
    }
}
