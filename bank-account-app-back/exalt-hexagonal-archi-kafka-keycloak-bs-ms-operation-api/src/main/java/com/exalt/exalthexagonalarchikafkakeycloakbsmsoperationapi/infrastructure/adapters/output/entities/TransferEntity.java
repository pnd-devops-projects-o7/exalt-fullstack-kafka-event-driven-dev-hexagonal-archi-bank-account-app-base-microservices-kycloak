package com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.output.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transfers")
public class TransferEntity {
    @Id
    private String transferId;
    private String originAccountId;
    private String destinationAccountId;
    private BigDecimal transferAmount;
    private String description;
    private Instant createdAt;
}
