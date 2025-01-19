package com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.input.web;

import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.ports.input.InputService;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.output.domain.dtos.AccountRequestDto;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.output.domain.dtos.AccountResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Collection;

@RestController
@RequestMapping(value = "/api/bank-account")
public class BankAccountApiWeb {
    private final InputService inputService;
    private static final Logger log = LoggerFactory.getLogger(BankAccountApiWeb.class.getName());

    public BankAccountApiWeb(final InputService inputService) {
        this.inputService = inputService;
    }

    @GetMapping(value = "/accounts")
    @PreAuthorize("hasAnyRole('MANAGER','ADMIN','OWNER','USER')")
    public ResponseEntity<Collection<AccountResponseDto>> getAllBankAccounts(){
        log.debug("list all created accounts");
        return ResponseEntity.ok().body(inputService.getAllBankAccounts());
    }
    @PostMapping(value = "/accounts")
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN','OWNER')")
    public ResponseEntity<AccountResponseDto> createBankAccount(@RequestBody AccountRequestDto accountRequestDto){
        log.debug("create an account {}", accountRequestDto);
        return ResponseEntity.ok().body(inputService.createAccount(accountRequestDto));
    }
    @GetMapping(value = "/accounts/{accountId}")
    @PreAuthorize("hasAnyRole('OWNER','ADMIN','MANAGER','USER')")
    public AccountResponseDto getAccountById(@PathVariable(value = "accountId") String accountId){
        log.debug("get an account based id {}", accountId);
        return inputService.getAccountById(accountId);
    }
    @PostMapping(value = "/accounts/switch-state/{accountId}")
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN','OWNER')")
    public ResponseEntity<AccountResponseDto> switchAccountState(@PathVariable(value = "accountId") String accountId){
        log.debug("suspend an account base on id {} from active state", accountId);
        return ResponseEntity.ok().body(inputService.switchAccountState(accountId));
    }
    @PostMapping(value = "/accounts/update-balance/{accountId}")
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN','OWNER')")
    public ResponseEntity<AccountResponseDto> updateBalanceCurrentAccount(@PathVariable(value = "accountId") String accountId,
                                                                            @RequestParam(value = "transaction-amount") BigDecimal transactionAmount){
        log.debug("update account balance id {}, transaction amount {}", accountId, transactionAmount);
        return ResponseEntity.ok().body(inputService.updateAccountBalance(accountId,transactionAmount));
    }

    @PostMapping(value = "/accounts/update-overdraft-irate/{accountId}")
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN','OWNER')")
    public ResponseEntity<AccountResponseDto> updateAccountOverdraftOrInterestRate(@PathVariable(value = "accountId") String accountId,
                                                                                   @RequestParam(value = "overdraft_irate") Double overdraftOrIrate){
        log.debug("update account overdraft / interest rate id {}, irate {}", accountId, overdraftOrIrate);
        return ResponseEntity.ok().body(inputService.updateAccountOverdraftOrInterestRate(accountId,overdraftOrIrate));
    }

}
