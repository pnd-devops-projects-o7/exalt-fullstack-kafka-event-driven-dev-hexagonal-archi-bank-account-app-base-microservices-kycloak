package com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.output;


import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.adapters.output.domain.dtos.AddressDto;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.adapters.output.domain.entities.AddressEntity;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.adapters.output.domain.entities.CustomerEntity;

import java.util.List;

public interface OutputService {
    void createCustomer(CustomerEntity customerEntity);

    List<CustomerEntity> loadAllPersistedCustomers();
    CustomerEntity findCustomerBy(String firstname, String lastname, String email);

    CustomerEntity getCustomerById(String customerId);

    void archiveCustomer(CustomerEntity customerEntity);
    void updateCustomerInfo(AddressEntity addressEntity, CustomerEntity customerEntity);
    AddressEntity getAddressByInfo(AddressDto addressDto);

    void createAddress(AddressEntity addressEntity);
}
