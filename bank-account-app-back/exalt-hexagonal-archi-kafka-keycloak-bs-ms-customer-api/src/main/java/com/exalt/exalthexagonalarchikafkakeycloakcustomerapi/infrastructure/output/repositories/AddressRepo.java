package com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.output.repositories;

import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.output.entities.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface AddressRepo extends JpaRepository<AddressEntity, UUID> {
}
