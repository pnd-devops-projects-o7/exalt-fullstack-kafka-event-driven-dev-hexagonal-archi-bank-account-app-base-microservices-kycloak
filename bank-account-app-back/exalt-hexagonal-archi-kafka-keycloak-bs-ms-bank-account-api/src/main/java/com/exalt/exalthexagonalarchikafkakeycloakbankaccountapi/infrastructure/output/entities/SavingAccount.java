package com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.output.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@DiscriminatorValue("saving")
public class SavingAccount extends BankAccountEntity{
    private double interestRate;

}
