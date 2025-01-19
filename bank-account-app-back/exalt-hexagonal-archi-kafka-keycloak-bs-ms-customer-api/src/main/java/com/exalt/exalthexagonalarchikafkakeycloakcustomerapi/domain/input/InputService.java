package com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.input;

import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.adapters.output.domain.dtos.AddressDto;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.adapters.output.domain.dtos.CustomerRequestDto;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.adapters.output.domain.dtos.CustomerResponseDto;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.adapters.output.domain.entities.AddressEntity;

import java.util.List;

public interface InputService {
    CustomerResponseDto createCustomer(CustomerRequestDto customerRequestDto);
    List<CustomerResponseDto> loadAllPersistedCustomers();
    CustomerResponseDto getCustomerBy(String firstname, String lastname, String email);
    CustomerResponseDto archiveCustomer(String customerId);
    CustomerResponseDto getCustomerById(String customerId);

    CustomerResponseDto updateCustomerInfo(String customerId, CustomerRequestDto customerRequestDto);
    AddressEntity getAddressByInfo(AddressDto addressDto);

    CustomerResponseDto customerSwitchState(String customerId);
}
