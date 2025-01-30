package com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.feign_clients.services;

import com.google.common.net.HttpHeaders;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
//this class is a request interceptor that operation api
//uses to send in feign request the jwt to remote bank-account and customer apis
public class OperationJtwInterceptor implements RequestInterceptor {
    private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());
    @Override
    public void apply(RequestTemplate requestTemplate) {
        final String authorization = HttpHeaders.AUTHORIZATION;
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;
        String jwtAccessToken = jwtAuthenticationToken.getToken().getTokenValue();
        if(jwtAccessToken!=null){
            requestTemplate.header(authorization,"Bearer "+jwtAccessToken);
        }
        else {
            log.error("no authentication token found");
        }
    }
}
