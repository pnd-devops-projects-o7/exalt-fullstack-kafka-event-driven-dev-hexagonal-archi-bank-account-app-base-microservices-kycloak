package com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.output.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "operations")
public class OperationEntity implements Serializable {
    @Id
    private String operationId;
    private String operationType;
    private BigDecimal transactionAmount;
    private Instant createdAt;
    private String description;
    private String accountId;
}
