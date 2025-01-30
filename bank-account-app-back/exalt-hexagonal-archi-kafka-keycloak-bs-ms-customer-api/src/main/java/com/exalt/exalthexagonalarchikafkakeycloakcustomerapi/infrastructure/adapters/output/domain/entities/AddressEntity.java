package com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.adapters.output.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "addresses")
@ToString
public class AddressEntity {
    @Id
    private String addressId;
    private int streetNum;
    private String streetName;
    private int postalCode;
    private String city;
    private String country;
    private String birthCountry;
}
