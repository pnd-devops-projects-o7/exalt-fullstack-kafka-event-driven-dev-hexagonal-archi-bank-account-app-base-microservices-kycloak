package com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.output.service;

import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.output.CustomerOutputService;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.output.entities.AddressEntity;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.output.entities.CustomerEntity;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.output.repositories.AddressRepo;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.output.repositories.CustomerRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class CustomerOutputServiceImpl implements CustomerOutputService {
    private final CustomerRepo customerRepo;
    private final AddressRepo addressRepo;
    @Value("${application.welcome-message}")
    private String welcome;
    @Value("${application.managed-operations.operation1}")
    private String op1;
    @Value("${application.managed-operations.operation2}")
    private String op2;
    @Value("${application.managed-operations.operation3}")
    private String op3;
    @Value("${application.managed-operations.operation4}")
    private String op4;

    public CustomerOutputServiceImpl(CustomerRepo customerRepo, AddressRepo addressRepo) {
        this.customerRepo = customerRepo;
        this.addressRepo = addressRepo;
    }

    @Override
    public void createCustomer(AddressEntity addressEntity, CustomerEntity customerEntity) {

        addressRepo.save(addressEntity);
        customerRepo.save(customerEntity);
    }

    @Override
    public List<CustomerEntity> loadAllPersistedCustomers() {
       return customerRepo.loadAllPersistedCustomers();
    }

    @Override
    public CustomerEntity findCustomerBy(String firstname, String lastname, String email) {
       return customerRepo.findCustomerBy(firstname,lastname,email);
    }

    @Override
    public CustomerEntity getCustomerById(UUID customerId) {
        return customerRepo.findCustomerById(customerId);
    }

    @Override
    public CustomerEntity archiveCustomer(CustomerEntity customerEntity) {
       return customerRepo.save(customerEntity);
    }

    @Override
    public void updateCustomerInfo(AddressEntity addressModel, CustomerEntity customerModel) {
        addressRepo.save(addressModel);
        customerRepo.save(customerModel);
    }

    @Override
    public Map<String, Map<String, String>> getWelcome() {
        return Map.of(welcome,
                Map.of("operation1",op1, "operation2", op2,"operation3", op3, "operation4", op4));
    }
}
