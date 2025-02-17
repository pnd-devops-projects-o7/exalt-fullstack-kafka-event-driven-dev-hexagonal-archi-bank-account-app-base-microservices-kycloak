package com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;
import reactor.util.annotation.NonNull;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final KeycloakJwtAuthenticationConverter keycloakJwtAuthenticationConverter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorization -> {
                            authorization.requestMatchers(
                                            "/swagger-ui/**",
                                            "/api-docs/**",
                                            "/actuator/**")
                                    .permitAll();
                            authorization.anyRequest().authenticated();
                        }

                );
        httpSecurity.oauth2ResourceServer(authorization ->
                authorization.jwt(jwtConfigurer ->
                        jwtConfigurer.jwtAuthenticationConverter(keycloakJwtAuthenticationConverter)));

        return httpSecurity.build();
    }

    @Component
    protected static class KeycloakJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {
        private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

        @Override
        public AbstractAuthenticationToken convert(@NonNull Jwt jwt) {
            Collection<GrantedAuthority> authorities = Stream.concat(
                    jwtGrantedAuthoritiesConverter.convert(jwt).stream(),
                    extractAuthorities(jwt).stream()
            ).collect(Collectors.toSet());

            return new JwtAuthenticationToken(jwt, authorities, jwt.getClaim("preferred_username"));
        }

        @Value("${security.oauth2.converter.keycloak.backend-gateway-client-id}")
        private String backendGatewayClientId;
        @Value("${security.oauth2.converter.keycloak.public-angular-app-client-id}")
        private String publicAngularAppClientId;

        private Collection<GrantedAuthority> extractAuthorities(Jwt jwt) {
            Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
            if (resourceAccess == null) {
                return Set.of();
            }
            Map<String, Object> clientIdRoles = null;
            if (resourceAccess.containsKey(publicAngularAppClientId)) {
                clientIdRoles = (Map<String, Object>) resourceAccess.get(publicAngularAppClientId);
            } else if (resourceAccess.containsKey(backendGatewayClientId)) {
                clientIdRoles = (Map<String, Object>) resourceAccess.get(backendGatewayClientId);
            }
            if (clientIdRoles == null) {
                return Set.of();
            }
            Collection<String> keycloakRoles = (Collection<String>) clientIdRoles.get("roles");
            //spring security roles from keycloak roles
            return keycloakRoles.stream()
                    .map(keycloakRole -> new SimpleGrantedAuthority("ROLE_" + keycloakRole))
                    .collect(Collectors.toSet());
        }
    }
}
