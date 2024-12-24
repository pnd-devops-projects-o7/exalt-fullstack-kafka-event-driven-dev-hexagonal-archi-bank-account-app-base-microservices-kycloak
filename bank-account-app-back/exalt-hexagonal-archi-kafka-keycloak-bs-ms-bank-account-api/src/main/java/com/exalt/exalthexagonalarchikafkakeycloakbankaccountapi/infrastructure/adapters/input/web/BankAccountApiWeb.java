package com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.input.web;

import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.ports.input.InputService;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.output.domain.dtos.AccountRequestDto;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.output.domain.dtos.AccountResponseDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/bank-account")
@RequiredArgsConstructor
public class BankAccountApiWeb {
    private final InputService inputService;
    private static final Logger LOGGER = LoggerFactory.getLogger(BankAccountApiWeb.class.getName());
    @GetMapping
    @PreAuthorize("hasAnyRole('CLIENT_USER','CLIENT_ADMIN','CLIENT_OWNER')")
    public ResponseEntity<Map<String, Map<String, String>>> welcome() {
        LOGGER.debug("get welcome message");
        return ResponseEntity.ok().body(inputService.getWelcome());
    }
    @GetMapping(value = "/accounts")
    @PreAuthorize("hasAnyRole('CLIENT_ADMIN','CLIENT_OWNER')")
    public ResponseEntity<Collection<AccountResponseDto>> getAllBankAccounts(){
        LOGGER.debug("list all created accounts");
        return ResponseEntity.ok().body(inputService.getAllBankAccounts());
    }
    @PostMapping(value = "/accounts")
    @PreAuthorize("hasAnyRole('CLIENT_ADMIN')")
    public ResponseEntity<AccountResponseDto> createBankAccount(@RequestBody AccountRequestDto accountRequestDto){
        LOGGER.debug("create an account {}", accountRequestDto);
        return ResponseEntity.ok().body(inputService.createAccount(accountRequestDto));
    }
    @GetMapping(value = "/accounts/{accountId}")
    @PreAuthorize("hasAnyRole('CLIENT_ADMIN','CLIENT_OWNER')")
    public AccountResponseDto getAccountById(@PathVariable(value = "accountId") String accountId){
        LOGGER.debug("get an account based id {}", accountId);
        return inputService.getAccountById(accountId);
    }
    @PostMapping(value = "/accounts/activate/{accountId}")
    public ResponseEntity<AccountResponseDto> activateAccount(@PathVariable(value = "accountId") String accountId){
        LOGGER.debug("activate an account base on id {} from created to active state", accountId);
        return ResponseEntity.ok().body(inputService.activateAccount(accountId));
    }
    @PostMapping(value = "/accounts/suspend/{accountId}")
    public ResponseEntity<AccountResponseDto> suspendAccount(@PathVariable(value = "accountId") String accountId){
        LOGGER.debug("suspend an account base on id {} from active state", accountId);
        return ResponseEntity.ok().body(inputService.suspendAccount(accountId));
    }
}
