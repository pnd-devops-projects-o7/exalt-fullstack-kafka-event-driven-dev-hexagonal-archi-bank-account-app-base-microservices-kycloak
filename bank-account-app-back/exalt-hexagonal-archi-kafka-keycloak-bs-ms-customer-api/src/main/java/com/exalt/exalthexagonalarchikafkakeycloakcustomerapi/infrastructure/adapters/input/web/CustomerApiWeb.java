package com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.adapters.input.web;

import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.input.InputService;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.adapters.output.domain.dtos.CustomerRequestDto;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.adapters.output.domain.dtos.CustomerResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/customer")
public class CustomerApiWeb {
    private final InputService inputService;
    private static final Logger log = LoggerFactory.getLogger(CustomerApiWeb.class.getName());

    public CustomerApiWeb(final InputService inputService) {
        this.inputService = inputService;
    }

    @PostMapping("/customers")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<CustomerResponseDto> createCustomer(@RequestBody CustomerRequestDto customerRequestDto) {
        log.debug("create a new customer {}",customerRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(inputService.createCustomer(customerRequestDto));
    }

    @GetMapping("/customers")
    // user and admin can call this api
    @PreAuthorize("hasAnyRole('OWNER','ADMIN','MANAGER','USER')")
    public ResponseEntity<List<CustomerResponseDto>> getAllCustomers() {
        log.debug("load all create customers");
        return ResponseEntity.ok().body(inputService.loadAllPersistedCustomers());
    }

    @GetMapping(value = "/customers/{customerId}")
    @PreAuthorize("hasAnyRole('OWNER','ADMIN','MANAGER','USER')")
    public CustomerResponseDto getCustomerById(@PathVariable(value = "customerId") String customerId) {
        log.debug("get a customer by id {}",customerId);
        return inputService.getCustomerById(customerId);
    }

    @GetMapping(value = "/customers/{firstname}/{lastname}/{email}")
    @PreAuthorize("hasAnyRole('OWNER','ADMIN','MANAGER','USER')")
    public ResponseEntity<CustomerResponseDto> getCustomerBy(@PathVariable(value = "firstname") final String firstname,
                                                             @PathVariable(value = "lastname") final String lastname,
                                                             @PathVariable(value = "email") final String email) {
        log.debug("get a customer by info");
        return ResponseEntity.ok().body(inputService.getCustomerBy(firstname, lastname, email));
    }

    @PostMapping(value = "/customers/archive/{customerId}")
    @PreAuthorize("hasAnyRole('OWNER','ADMIN','MANAGER')")
    public ResponseEntity<CustomerResponseDto> archiveCustomer(@PathVariable(value = "customerId") String customerId) {
        log.debug("archive customer {}",customerId);
        return ResponseEntity.ok().body(inputService.archiveCustomer(customerId));
    }
    @PostMapping(value = "/customers/switch-state/{customerId}")
    @PreAuthorize("hasAnyRole('OWNER','ADMIN','MANAGER')")
    public ResponseEntity<CustomerResponseDto> customerSwitchState(@PathVariable(value = "customerId") String customerId){
        log.debug("suspend customer {}",customerId);
        return ResponseEntity.ok().body(inputService.customerSwitchState(customerId));
    }

    @PutMapping(value = "/customers/{customerId}")
    // only owner can call this api
    @PreAuthorize("hasAnyRole('OWNER','ADMIN','MANAGER')")
    public ResponseEntity<CustomerResponseDto> updateCustomerInfo(@PathVariable(value = "customerId") String customerId,
                                                                  @RequestBody CustomerRequestDto customerRequestDto) {
        log.debug( "update a customer {}",customerRequestDto);
        return ResponseEntity.ok().body(inputService.updateCustomerInfo(customerId, customerRequestDto));
    }
}
