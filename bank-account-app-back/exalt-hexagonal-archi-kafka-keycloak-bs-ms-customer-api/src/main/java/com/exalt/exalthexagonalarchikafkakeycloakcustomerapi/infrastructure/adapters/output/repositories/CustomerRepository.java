package com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.adapters.output.repositories;

import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.adapters.output.domain.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, String> {
    @Query(value = "select * from customer_api_db.customers cs order by cs.created_at desc", nativeQuery = true)
    List<CustomerEntity> loadAllPersistedCustomers();
    @Query(value = "select * from customer_api_db.customers as c where c.firstname=:firstname and c.lastname=:lastname " +
            "and c.email=:email", nativeQuery = true)
    CustomerEntity findCustomerBy(@Param("firstname") String firstname, @Param("lastname") String lastname, @Param("email") String email);
    @Query(value = "select * from customer_api_db.customers c where c.customer_id=:customerId", nativeQuery = true)
    CustomerEntity findCustomerById(@Param("customerId") String customerId);
}