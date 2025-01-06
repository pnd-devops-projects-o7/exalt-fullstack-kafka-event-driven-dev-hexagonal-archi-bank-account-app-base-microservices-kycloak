package com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.security_config;

import lombok.NonNull;
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
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class AccountApiSecurityConfig {
    private final CustomKeycloakAUthenticationConverter keycloakAuthenticationConverter;

    public AccountApiSecurityConfig(CustomKeycloakAUthenticationConverter keycloakAuthenticationConverter) {
        this.keycloakAuthenticationConverter = keycloakAuthenticationConverter;
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
            /* instead JWT default converter we define a custom JWT converter to convert Keycloak roles to Spring security roles*/
            authorization.jwt(configurer ->
                /*inject custom Jwt converter*/
                configurer.jwtAuthenticationConverter(keycloakAuthenticationConverter)
            )
        );
        return httpSecurity.build();
    }
    @Component
    public static class CustomKeycloakAUthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken>{
        @Value("${security.oauth2.converter.keycloak.client-id}")
        String keycloakClientId;
        @Value("${security.oauth2.converter.jwt.preferred-username}")
        String preferredUsername;
        private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        @Override
        public AbstractAuthenticationToken convert(@NonNull Jwt jwt) {
            Collection<GrantedAuthority> authorities = Stream.concat(
                    jwtGrantedAuthoritiesConverter.convert(jwt).stream(),
                    extractAuthorities(jwt).stream())
                    .collect(Collectors.toSet());
            return new JwtAuthenticationToken(jwt,authorities, getPrincipeClaimName(jwt));
        }

        private String getPrincipeClaimName(Jwt jwt) {
            String claimName = JwtClaimNames.SUB;
            if(preferredUsername!=null){
                claimName = preferredUsername;
            }
            return jwt.getClaim(claimName);
        }

        @SuppressWarnings("unchecked")
        private Collection<GrantedAuthority> extractAuthorities(@NonNull Jwt jwt) {
            if (jwt.getClaim("resource_access") == null) {
                return Set.of();
            }
            Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
            if (resourceAccess.get(keycloakClientId) == null) {
                return Set.of();
            }
            Map<String, Object> clientIdRoles = (Map<String, Object>) resourceAccess.get(keycloakClientId);
            Collection<String> keycloakRoles = (Collection<String>) clientIdRoles.get("roles");
            //map keycloak roles into spring security roles
            return keycloakRoles.stream()
                    .map(keycloakRole -> new SimpleGrantedAuthority("ROLE_" + keycloakRole))
                    .collect(Collectors.toSet());
        }
    }

}
