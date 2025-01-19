package com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.adapters.output.repositories;

import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.adapters.output.domain.entities.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, String> {
    @Query(value = "select * from customer_api_db.addresses addr where addr.street_num=:streetNum and addr.street_name=:streetName and addr.postal_code=:postalCode and addr.city=:city and addr.country=:country", nativeQuery = true)
    AddressEntity getAddressByInfo(@Param("streetNum") int streetNum, @Param("streetName") String streetName,
            @Param("postalCode") int postalCode, @Param("city") String city, @Param("country") String country);
}
