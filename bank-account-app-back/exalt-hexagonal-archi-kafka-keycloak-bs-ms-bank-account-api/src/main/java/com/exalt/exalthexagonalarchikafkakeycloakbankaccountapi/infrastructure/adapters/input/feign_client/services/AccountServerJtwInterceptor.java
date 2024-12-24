package com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.input.feign_client.services;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
//this class is a request interceptor that account api
//uses to send in feign request the jwt to remote
 // customer api
public class AccountServerJtwInterceptor implements RequestInterceptor{
    @Override
    public void apply(RequestTemplate requestTemplate) {
        final RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            final HttpServletRequest httpServletRequest = ((ServletRequestAttributes) requestAttributes).getRequest();
            requestTemplate.header(HttpHeaders.AUTHORIZATION, httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION));
        }
    }
}
