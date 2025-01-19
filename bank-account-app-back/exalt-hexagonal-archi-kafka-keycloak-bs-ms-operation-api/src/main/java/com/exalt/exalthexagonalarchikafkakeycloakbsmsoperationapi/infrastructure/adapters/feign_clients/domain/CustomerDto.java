package com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.feign_clients.domain;


import java.time.Instant;

public record CustomerDto (String customerId, String firstname, String lastname, String email,
                           String customerState, Instant createdAt){
}
