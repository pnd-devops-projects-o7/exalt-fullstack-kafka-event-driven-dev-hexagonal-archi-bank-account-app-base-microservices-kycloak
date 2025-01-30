package com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.output.domain.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@DiscriminatorValue("current")
@Builder
public class CurrentAccount extends BankAccountEntity{
    private double overdraft;
}
