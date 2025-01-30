package com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.feign_client.services;

import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.feign_client.domain.CustomerResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "${remote-client.name}", url = "${remote-client.base-url}",
        path = "${remote-client.context-path}", configuration = JwtInterceptorPropagator.class,
fallback = void.class)
public interface RemoteClientService {
    @GetMapping(value = "/customers/{customerId}")
    CustomerResponseDto getRemoteCustomerById(@PathVariable(value = "customerId") String customerId);
}
