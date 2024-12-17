package com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.output.entities;

import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.AccountState;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.input.clients.entity.CustomerEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "bank_accounts")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", length = 7)
public abstract class BankAccountEntity {
    @Id
    private UUID accountId;
    private AccountState accountState;
    private BigDecimal balance;
    private Instant createdAt;
    @Transient
    private CustomerEntity customerEntity;
}
