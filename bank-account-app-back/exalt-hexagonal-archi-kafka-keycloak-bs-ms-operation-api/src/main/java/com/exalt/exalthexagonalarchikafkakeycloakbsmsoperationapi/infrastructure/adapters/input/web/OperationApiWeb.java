package com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.input.web;

import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.ports.input.OperationInputService;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.ports.input.TransferInputService;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.output.dtos.OperationRequestDto;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.output.dtos.OperationResponseDto;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.output.dtos.TransferRequestDto;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.output.dtos.TransferResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Collection;

@RestController
@RequestMapping(value = "/api/operation")
public class OperationApiWeb {
    private final OperationInputService operationInputService;
    private final TransferInputService transferInputService;
    private static final Logger log = LoggerFactory.getLogger(OperationApiWeb.class.getSimpleName());

    public OperationApiWeb(final OperationInputService operationInputService, final TransferInputService transferInputService) {
        this.operationInputService = operationInputService;
        this.transferInputService = transferInputService;
    }

    @GetMapping(value = "/operations")
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN','OWNER', 'USER')")
    public ResponseEntity<Collection<OperationResponseDto>> getAllOperations() {
        log.debug("load all operations");
        return ResponseEntity.ok().body(operationInputService.getAllOperations());
    }

    @PostMapping(value = "/operations")
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN','OWNER')")
    public ResponseEntity<OperationResponseDto> createOperation(@RequestBody OperationRequestDto operationRequestDto,
                                                                @RequestParam("transaction-amount") BigDecimal transactionAmount) {
        log.debug("create an operation {} with transaction amount {}", operationRequestDto, transactionAmount);
        return ResponseEntity.ok().body(operationInputService.createOperation(operationRequestDto, transactionAmount));
    }

    @GetMapping(value = "/operations/{operationId}")
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN','OWNER')")
    public ResponseEntity<OperationResponseDto> getOperationById(@PathVariable(value = "operationId") String operationId) {
        log.debug("get operation by id {}", operationId);
        return ResponseEntity.ok().body(operationInputService.getOperationById(operationId));
    }

    @PostMapping(value = "/transfers")
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN','OWNER')")
    public ResponseEntity<TransferResponseDto> createTransfer(@RequestBody TransferRequestDto transferRequestDto) {
        log.debug("create a transfer {} ", transferRequestDto);
        return ResponseEntity.ok().body(transferInputService.createTransfer(transferRequestDto));
    }

    @GetMapping(value = "/transfers")
    @PreAuthorize("hasAnyRole('OWNER','ADMIN','MANAGER','USER')")
    public ResponseEntity<Collection<TransferResponseDto>> getAllTransfers() {
        log.debug("load all transfers operations");
        return ResponseEntity.ok().body(transferInputService.getAllTransfers());
    }

    @GetMapping(value = "/transfers/{transferId}")
    @PreAuthorize("hasAnyRole('OWNER','ADMIN','MANAGER','USER')")
    public ResponseEntity<TransferResponseDto> getTransfer(@PathVariable(name = "transferId") String transferId) {
        log.debug("load a transfer operation by id {} ", transferId);
        return ResponseEntity.ok().body(transferInputService.getTransfer(transferId));
    }
}
