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
    @PreAuthorize("hasAnyRole('CLIENT_ADMIN','CLIENT_OWNER','CLIENT_USER')")
    public ResponseEntity<Collection<AccountResponseDto>> getAllBankAccounts(){
        log.debug("list all created accounts");
        return ResponseEntity.ok().body(inputService.getAllBankAccounts());
    }
    @PostMapping(value = "/accounts")
    @PreAuthorize("hasAnyRole('CLIENT_ADMIN','CLINET_OWNER')")
    public ResponseEntity<AccountResponseDto> createBankAccount(@RequestBody AccountRequestDto accountRequestDto){
        log.debug("create an account {}", accountRequestDto);
        return ResponseEntity.ok().body(inputService.createAccount(accountRequestDto));
    }
    @GetMapping(value = "/accounts/{accountId}")
    @PreAuthorize("hasAnyRole('CLIENT_ADMIN','CLIENT_OWNER')")
    public AccountResponseDto getAccountById(@PathVariable(value = "accountId") String accountId){
        log.debug("get an account based id {}", accountId);
        return inputService.getAccountById(accountId);
    }
    @PostMapping(value = "/accounts/suspend/{accountId}")
    @PreAuthorize("hasAnyRole('CLIENT_ADMIN','CLINET_OWNER')")
    public ResponseEntity<AccountResponseDto> suspendAccount(@PathVariable(value = "accountId") String accountId){
        log.debug("suspend an account base on id {} from active state", accountId);
        return ResponseEntity.ok().body(inputService.suspendAccount(accountId));
    }
    @PostMapping(value = "/accounts/update-balance/{accountId}")
    @PreAuthorize("hasAnyRole('CLIENT_ADMIN','CLINET_OWNER')")
    public ResponseEntity<AccountResponseDto> updateAccountBalance(@PathVariable(value = "accountId") String accountId, @RequestParam(value = "amount") BigDecimal amount){
        log.debug("update account balance id {}, amount {}", accountId, amount);
        return ResponseEntity.ok().body(inputService.updateAccountBalance(accountId,amount));
    }

    @PostMapping(value = "/accounts/update-irate/{accountId}")
    @PreAuthorize("hasAnyRole('CLIENT_ADMIN','CLINET_OWNER')")
    public ResponseEntity<AccountResponseDto> updateAccountInterestRate(@PathVariable(value = "accountId") String accountId, @RequestParam(value = "irate") Double irate){
        log.debug("update account interest rate id {}, irate {}", accountId, irate);
        return ResponseEntity.ok().body(inputService.updateAccountInterestRate(accountId,irate));
    }

    @PostMapping(value = "/accounts/update-overdraft/{accountId}")
    @PreAuthorize("hasAnyRole('CLIENT_ADMIN','CLINET_OWNER')")
    public ResponseEntity<AccountResponseDto> updateAccountOverdraft(@PathVariable(value = "accountId") String accountId, @RequestParam(value = "overdraft") Double overdraft){
        log.debug("update account overdraft id {}, amount {}", accountId, overdraft);
        return ResponseEntity.ok().body(inputService.updateAccountOverdraft(accountId,overdraft));
    }
}
