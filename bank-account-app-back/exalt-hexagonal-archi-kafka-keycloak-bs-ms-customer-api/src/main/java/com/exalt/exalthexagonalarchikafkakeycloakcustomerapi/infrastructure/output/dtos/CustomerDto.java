package com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.output.dtos;

import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.avro_beans.CustomerState;

import java.time.Instant;
import java.util.UUID;

public record CustomerDto (UUID customerId, String firstname, String lastname, String email, CustomerState customerState, Instant createdAt){}
