package com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.security_config;

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

    private final KeycloakAuthenticationConverter keyCloakJwtAuthConverter;

    public SecurityConfig(KeycloakAuthenticationConverter keyCloakJwtAuthConverter) {
        this.keyCloakJwtAuthConverter = keyCloakJwtAuthConverter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorization ->{
                    authorization.requestMatchers("/swagger-ui/**","/api-docs/**")
                            .permitAll();
                    authorization.anyRequest().authenticated();
                });

        httpSecurity.oauth2ResourceServer(authorization ->
                // authorization.jwt(Customizer.withDefaults()); /*this uses default converter of Spring Security*/
                /* instead JWT default converter we define a JWT converter to convert Keycloak roles into Spring security roles*/
                authorization.jwt(configurer ->
                        /*add defined Jwt converter: jwtAuthConverter*/
                        configurer.jwtAuthenticationConverter(keyCloakJwtAuthConverter)
                )
        );
        return httpSecurity.build();
    }
}
