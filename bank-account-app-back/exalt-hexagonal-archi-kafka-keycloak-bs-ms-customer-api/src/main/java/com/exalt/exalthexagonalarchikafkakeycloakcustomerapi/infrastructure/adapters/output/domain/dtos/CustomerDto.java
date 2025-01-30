package com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.adapters.output.domain.dtos;


import java.time.Instant;

public record CustomerDto (String customerId, String firstname, String lastname, String email, String customerState, Instant createdAt){}
