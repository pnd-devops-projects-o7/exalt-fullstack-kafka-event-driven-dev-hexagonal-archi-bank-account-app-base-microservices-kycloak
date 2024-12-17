package com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.output.entities;

import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.avro_beans.CustomerState;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

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
    private UUID customerId;
    private  String firstname;
    private String lastname;
    private String email;
    @Enumerated(EnumType.STRING)
    private CustomerState state;
    private Instant createdAt;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    private AddressEntity addressEntity;
}