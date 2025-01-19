package com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.feign_client.domain;


import java.time.Instant;

public record CustomerDto (String customerId, String firstname, String lastname, String email, String customerState, Instant createdAt){}
