package com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.input.feign_client.services;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
//this class is a request interceptor that account api
//uses to send in feign request the jwt to remote
 // customer api
@Slf4j
public class FeignJwtInterceptor implements RequestInterceptor{
    @Override
    public void apply(RequestTemplate requestTemplate) {
        final String authorization = HttpHeaders.AUTHORIZATION;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof JwtAuthenticationToken jwtAuthenticationToken){
            String tokenValue = jwtAuthenticationToken.getToken().getTokenValue();
            requestTemplate.header(authorization,"Bearer "+tokenValue);
        }
        else {
            log.error("!!!!!!!!!!!! no authentication token found");
        }
    }
}
