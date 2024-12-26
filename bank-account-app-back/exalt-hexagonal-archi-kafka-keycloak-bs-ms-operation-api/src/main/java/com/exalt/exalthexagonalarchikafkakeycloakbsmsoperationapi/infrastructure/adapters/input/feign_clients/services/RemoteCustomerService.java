package com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.input.feign_clients.services;

import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.input.feign_clients.domain.CustomerResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        value = "${feign-client.customer-api.value}",
        url = "${feign-client.customer-api.complete-url.base-url}",
        path = "${feign-client.customer-api.complete-url.path}",
        configuration = OperationJtwInterceptor.class)
public interface RemoteCustomerService {
    @GetMapping(value = "/customers/{customerId}")
    CustomerResponseDto getRemoteCustomerById(@PathVariable(value = "customerId") String customerId);
}
