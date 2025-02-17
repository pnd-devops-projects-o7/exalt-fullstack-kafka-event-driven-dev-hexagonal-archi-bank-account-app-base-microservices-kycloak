package com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.security_config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private final KeycloakAuthenticationConverter keycloakAuthenticationConverter;

    public SecurityConfig(KeycloakAuthenticationConverter keycloakAuthenticationConverter) {
        this.keycloakAuthenticationConverter = keycloakAuthenticationConverter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorization -> {
                    authorization.requestMatchers(
                                    "/swagger-ui/**",
                                    "/api-docs/**",
                                    "/actuator/**")
                            .permitAll();
                    authorization.anyRequest().authenticated();
                });
        httpSecurity.oauth2ResourceServer(authorization ->
                /* instead JWT default converter we define a custom JWT converter to convert Keycloak roles to Spring security roles*/
                authorization.jwt(configurer ->
                        /*inject custom Jwt converter*/
                        configurer.jwtAuthenticationConverter(keycloakAuthenticationConverter)
                )
        );
        return httpSecurity.build();
    }
}
