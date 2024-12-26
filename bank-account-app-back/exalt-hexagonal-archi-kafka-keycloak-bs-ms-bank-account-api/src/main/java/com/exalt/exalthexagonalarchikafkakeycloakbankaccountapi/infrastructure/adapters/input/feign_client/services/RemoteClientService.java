package com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.input.feign_client.services;

import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.input.feign_client.domain.CustomerResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        value = "${feign-client.value}",
        url = "${feign-client.complete-url.base-url}",
        path = "${feign-client.complete-url.path}",
        configuration = AccountJtwInterceptor.class)
public interface RemoteClientService {
    @GetMapping(value = "/customers/{customerId}")
    CustomerResponseDto getRemoteCustomerById(@PathVariable(name = "customerId") String customerId);
}
