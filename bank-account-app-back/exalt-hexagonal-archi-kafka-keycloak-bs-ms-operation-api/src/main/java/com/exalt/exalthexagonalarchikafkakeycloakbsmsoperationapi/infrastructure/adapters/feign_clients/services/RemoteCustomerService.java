package com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.feign_clients.services;

import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.feign_clients.domain.CustomerResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "${remote-client.client2.name}",
        url = "${remote-client.client2.base-url}",
        path = "${remote-client.client2.context-path}",
        configuration = OperationJtwInterceptor.class)
public interface RemoteCustomerService {
    @GetMapping(value = "/customers/{customerId}")
    CustomerResponseDto getRemoteCustomerById(@PathVariable(value = "customerId") String customerId);
}
