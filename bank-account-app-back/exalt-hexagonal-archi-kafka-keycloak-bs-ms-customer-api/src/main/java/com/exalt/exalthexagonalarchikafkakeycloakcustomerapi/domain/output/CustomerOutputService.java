package com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.output;


import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.output.entities.AddressEntity;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.output.entities.CustomerEntity;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface CustomerOutputService {
    void createCustomer(AddressEntity addressEntity, CustomerEntity customerEntity);

    List<CustomerEntity> loadAllPersistedCustomers();
    CustomerEntity findCustomerBy(String firstname, String lastname, String email);

    CustomerEntity getCustomerById(UUID customerId);

    CustomerEntity archiveCustomer(CustomerEntity customerEntity);
    void updateCustomerInfo(AddressEntity addressEntity, CustomerEntity customerEntity);

    Map<String, Map<String, String>> getWelcome();
}
