package com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.feign_client.services;

import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.feign_client.domain.CustomerResponseDto;
import com.google.common.net.HttpHeaders;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "${remote-client.name}", url = "${remote-client.base-url}",
        path = "${remote-client.context-path}", configuration = RemoteClientService.JwtInterceptorPropagator.class)
public interface RemoteClientService {
    @GetMapping(value = "/customers/{customerId}")
    CustomerResponseDto getRemoteCustomerById(@PathVariable(value = "customerId") String customerId);

    //this class is a request interceptor that account api uses to intercept and propagate jwt to remote customer api
    class JwtInterceptorPropagator implements RequestInterceptor {
        private static final Logger log = LoggerFactory.getLogger(CustomerResponseDto.class.getSimpleName());
        @Override
        public void apply(RequestTemplate requestTemplate) {
            final String authorization = HttpHeaders.AUTHORIZATION;
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication instanceof JwtAuthenticationToken jwtAuthenticationToken) {
                String tokenValue = jwtAuthenticationToken.getToken().getTokenValue();
                requestTemplate.header(authorization, "Bearer " + tokenValue);
            } else {
                log.error("no authentication token found");
            }
        }
    }
}
