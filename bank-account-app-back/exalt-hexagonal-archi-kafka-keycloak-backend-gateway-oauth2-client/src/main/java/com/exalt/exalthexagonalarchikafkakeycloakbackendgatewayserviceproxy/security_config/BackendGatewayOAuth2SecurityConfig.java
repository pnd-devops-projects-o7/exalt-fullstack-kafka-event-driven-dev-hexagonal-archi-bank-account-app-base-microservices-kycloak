package com.exalt.exalthexagonalarchikafkakeycloakbackendgatewayserviceproxy.security_config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class BackendGatewayOAuth2SecurityConfig {
    @Bean
    public SecurityWebFilterChain filterChain(ServerHttpSecurity httpSecurity){
        httpSecurity
                .authorizeExchange(auth-> auth.anyExchange().authenticated())
                .oauth2Login(Customizer.withDefaults())
                .oauth2ResourceServer(oauth2->
                        oauth2.opaqueToken(Customizer.withDefaults()));
        httpSecurity.csrf(ServerHttpSecurity.CsrfSpec::disable);

        return httpSecurity.build();
    }
}


