package com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.adapters.output.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers")
@ToString
public class CustomerEntity{
    @Id
    private String customerId;
    private  String firstname;
    private String lastname;
    private String email;
    private String state;
    private Instant createdAt;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    private AddressEntity addressEntity;
}