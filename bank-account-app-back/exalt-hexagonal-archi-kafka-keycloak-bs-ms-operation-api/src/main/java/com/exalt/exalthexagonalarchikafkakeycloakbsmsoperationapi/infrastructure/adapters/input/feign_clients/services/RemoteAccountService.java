package com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.input.feign_clients.services;

import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.input.feign_clients.domain.AccountResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@FeignClient(
        value = "${feign-client.bank-account-api.value}",
        url = "${feign-client.bank-account-api.complete-url.base-url}",
        path = "${feign-client.bank-account-api.complete-url.path}",
        configuration = OperationJtwInterceptor.class)
public interface RemoteAccountService {
    @GetMapping(value = "/accounts/{accountId}")
    AccountResponseDto getRemoteAccountById(@PathVariable(value = "accountId") String accountId);
    @PostMapping(value = "/accounts/update-balance/{accountId}")
    void updateAccountBalance(@PathVariable(value = "accountId") String accountId, @RequestParam(value = "amount") BigDecimal amount);
}
