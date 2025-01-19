package com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.feign_clients.services;

import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.feign_clients.domain.AccountResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@FeignClient(
        name = "${remote-client.client1.name}",
        url = "${remote-client.client1.base-url}",
        path = "${remote-client.client1.context-path}",
        configuration = OperationJtwInterceptor.class)
public interface RemoteAccountService {
    @GetMapping(value = "/accounts/{accountId}")
    AccountResponseDto getRemoteAccountById(@PathVariable(value = "accountId") String accountId);
    @PostMapping(value = "/accounts/update-balance/{accountId}")
    void updateBalanceCurrentAccount(
            @PathVariable(value = "accountId") String accountId,
            @RequestParam(value = "transaction-amount") BigDecimal amount);
}
