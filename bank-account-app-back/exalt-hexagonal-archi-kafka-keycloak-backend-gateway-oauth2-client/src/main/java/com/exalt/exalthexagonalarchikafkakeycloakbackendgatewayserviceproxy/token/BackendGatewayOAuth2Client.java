package com.exalt.exalthexagonalarchikafkakeycloakbackendgatewayserviceproxy.token;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.logging.Logger;

@RestController
public class BackendGatewayOAuth2Client {
    private final Logger logger = java.util.logging.Logger.getLogger(this.getClass().getSimpleName());

    @GetMapping(value = "/token")
    public Mono<String> getHome(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient) {
        logger.info("getting jwt token from authenticated client");
        return Mono.just(authorizedClient.getAccessToken().getTokenValue());
    }
}
