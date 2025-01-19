package com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.adapters.output.service;

import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.output.OutputService;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.adapters.output.domain.dtos.AddressDto;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.adapters.output.domain.entities.AddressEntity;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.adapters.output.domain.entities.CustomerEntity;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.adapters.output.repositories.AddressRepository;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.adapters.output.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OutputServiceImpl implements OutputService {
    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;

    public OutputServiceImpl(final CustomerRepository customerRepository,final AddressRepository addressRepository) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public void createCustomer(CustomerEntity customerEntity) {

        customerRepository.save(customerEntity);
    }

    @Override
    public List<CustomerEntity> loadAllPersistedCustomers() {
       return customerRepository.loadAllPersistedCustomers();
    }

    @Override
    public CustomerEntity findCustomerBy(String firstname, String lastname, String email) {
       return customerRepository.findCustomerBy(firstname,lastname,email);
    }

    @Override
    public CustomerEntity getCustomerById(String customerId) {
        return customerRepository.findCustomerById(customerId);
    }

    @Override
    public void archiveCustomer(CustomerEntity customerEntity) {
        customerRepository.save(customerEntity);
    }

    @Override
    public void updateCustomerInfo(AddressEntity addressModel, CustomerEntity customerModel) {
        addressRepository.save(addressModel);
        customerRepository.save(customerModel);
    }
    @Override
    public AddressEntity getAddressByInfo(AddressDto addressDto) {
        return addressRepository.getAddressByInfo(addressDto.streetNum(),
                addressDto.streetName(), addressDto.postalCode(), addressDto.city(), addressDto.country());
    }

    @Override
    public void createAddress(AddressEntity addressEntity) {
        addressRepository.save(addressEntity);
    }
}
