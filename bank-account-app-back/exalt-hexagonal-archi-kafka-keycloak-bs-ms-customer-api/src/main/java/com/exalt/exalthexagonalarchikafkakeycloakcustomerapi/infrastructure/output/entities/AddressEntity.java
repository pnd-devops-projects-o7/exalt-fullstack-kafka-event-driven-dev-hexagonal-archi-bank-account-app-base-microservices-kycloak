package com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.output.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

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
    private UUID addressId;
    private int streetNum;
    private String streetName;
    private int postalCode;
    private String city;
    private String country;
    private String birthCountry;
}
