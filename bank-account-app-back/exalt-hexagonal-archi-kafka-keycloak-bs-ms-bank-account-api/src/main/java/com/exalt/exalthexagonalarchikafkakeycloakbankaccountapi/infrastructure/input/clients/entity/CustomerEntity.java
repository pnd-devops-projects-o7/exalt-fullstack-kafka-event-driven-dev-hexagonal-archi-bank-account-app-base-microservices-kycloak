package com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.input.clients.entity;

import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.customer.CustomerState;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;
@Builder
@Setter
@Getter
public class CustomerEntity {
    private UUID customerId;
    private String firstname;
    private String lastname;
    private String email;
    private CustomerState customerState;
    private Instant createdAt;
}
