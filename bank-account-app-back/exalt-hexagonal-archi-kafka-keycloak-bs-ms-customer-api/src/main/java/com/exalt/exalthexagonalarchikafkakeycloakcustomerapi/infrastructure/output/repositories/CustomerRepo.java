package com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.output.repositories;

import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.output.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CustomerRepo extends JpaRepository<CustomerEntity, UUID> {
    @Query(value = "select * from customer_api_db.customers cs", nativeQuery = true)
    List<CustomerEntity> loadAllPersistedCustomers();
    @Query(value = "select * from customer_api_db.customers as c where c.firstname=:firstname and c.lastname=:lastname " +
            "and c.email=:email", nativeQuery = true)
    CustomerEntity findCustomerBy(@Param("firstname") String firstname, @Param("lastname") String lastname, @Param("email") String email);
    @Query(value = "select * from customer_api_db.customers c where c.customer_id=:customerId", nativeQuery = true)
    CustomerEntity findCustomerById(@Param("customerId") UUID customerId);
}