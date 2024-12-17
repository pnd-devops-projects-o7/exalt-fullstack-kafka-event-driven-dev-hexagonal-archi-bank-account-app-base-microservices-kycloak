package com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.input.web;

import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.input.InputService;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.output.dtos.CustomerRequestDto;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.output.dtos.CustomerResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/customer")
@RequiredArgsConstructor
public class CustomerApiWeb {
    private final InputService inputService;

    @GetMapping
    @PreAuthorize("hasAnyRole('CLIENT_USER','CLIENT_ADMIN','CLIENT_OWNER')")
    public ResponseEntity<Map<String, Map<String, String>>> welcome() {
        return ResponseEntity.ok().body(inputService.getWelcome());
    }

    @PostMapping("/customers")
    // only admin and owner can call this api
    @PreAuthorize("hasRole('CLIENT_ADMIN')")
    public ResponseEntity<CustomerResponseDto> createCustomer(@RequestBody CustomerRequestDto customerRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(inputService.createCustomer(customerRequestDto));
    }

    @GetMapping("/customers")
    // user and admin can call this api
    @PreAuthorize("hasAnyRole('CLIENT_ADMIN, CLIENT_OWNER')")
    public ResponseEntity<List<CustomerResponseDto>> getAllCustomers() {
        return ResponseEntity.ok().body(inputService.loadAllPersistedCustomers());
    }

    @GetMapping(value = "/customers/{customerId}")
    @PreAuthorize("hasRole('CLIENT_OWNER')")
    public ResponseEntity<CustomerResponseDto> getCustomerById(@PathVariable(name = "customerId") UUID customerId) {
        return ResponseEntity.ok().body(inputService.getCustomerById(customerId));
    }

    @GetMapping(value = "/customers/{firstname}/{lastname}/{email}")
    // only owner can call this api
    @PreAuthorize("hasRole('CLIENT_OWNER')")
    public ResponseEntity<CustomerResponseDto> getCustomerBy(@PathVariable(name = "firstname") final String firstname,
                                                             @PathVariable(name = "lastname") final String lastname,
                                                             @PathVariable(name = "email") final String email) {
        return ResponseEntity.ok().body(inputService.getCustomerBy(firstname, lastname, email));
    }

    @PostMapping(value = "/customers/archive/{customerId}")
    // only owner and admin can call this api
    @PreAuthorize("hasAnyRole('CLIENT_OWNER','CLIENT_ADMIN')")
    public ResponseEntity<CustomerResponseDto> archiveCustomer(@PathVariable(name = "customerId") UUID customerId) {
        return ResponseEntity.ok().body(inputService.archiveCustomer(customerId));
    }

    @PutMapping(value = "/customers/update/{customerId}")
    // only owner can call this api
    @PreAuthorize("hasRole('CLIENT_OWNER')")
    public ResponseEntity<CustomerResponseDto> updateCustomerInfo(@PathVariable(name = "customerId") UUID customerId,
                                                                  @RequestBody CustomerRequestDto customerRequestDto) {
        return ResponseEntity.ok().body(inputService.updateCustomerInfo(customerId, customerRequestDto));
    }
}
