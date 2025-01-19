package com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.usecase;

import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.avro_beans.Address;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.avro_beans.Customer;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.avro_beans.CustomerEvent;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.adapters.output.domain.dtos.AddressDto;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.adapters.output.domain.dtos.CustomerDto;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.adapters.output.domain.dtos.CustomerRequestDto;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.adapters.output.domain.dtos.CustomerResponseDto;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.adapters.output.domain.entities.AddressEntity;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.adapters.output.domain.entities.CustomerEntity;

import java.time.Instant;
import java.util.UUID;

public class MapperService {
    private MapperService() {
    }

    public static Customer mapFromRequestDto(CustomerRequestDto customerRequestDto) {
        return Customer.newBuilder()
                .setCustomerId(UUID.randomUUID().toString())
                .setFirstname(customerRequestDto.customerDto().firstname())
                .setLastname(customerRequestDto.customerDto().lastname())
                .setEmail(customerRequestDto.customerDto().email())
                .setCustomerState("ACTIVE")
                .setCreatedAt(Instant.now())
                .setAddress(Address.newBuilder()
                        .setAddressId(UUID.randomUUID().toString())
                        .setStreetNum(customerRequestDto.addressDto().streetNum())
                        .setStreetName(customerRequestDto.addressDto().streetName())
                        .setPostalCode(customerRequestDto.addressDto().postalCode())
                        .setCity(customerRequestDto.addressDto().city())
                        .setCountry(customerRequestDto.addressDto().country())
                        .setBirthCountry(customerRequestDto.addressDto().birthCountry())
                        .build())
                .build();
    }

    public static CustomerEntity mapFromCustomer(Customer customer) {
        return CustomerEntity.builder()
                .customerId(customer.getCustomerId())
                .firstname(customer.getFirstname())
                .lastname(customer.getLastname())
                .email(customer.getEmail())
                .state(customer.getCustomerState())
                .createdAt(customer.getCreatedAt())
                .addressEntity(AddressEntity.builder()
                        .addressId(customer.getAddress().getAddressId())
                        .streetNum(customer.getAddress().getStreetNum())
                        .streetName(customer.getAddress().getStreetName())
                        .postalCode(customer.getAddress().getPostalCode())
                        .city(customer.getAddress().getCity())
                        .country(customer.getAddress().getCountry())
                        .birthCountry(customer.getAddress().getBirthCountry())
                        .build())
                .build();
    }

    public static CustomerResponseDto mapFromCustomerEntity(CustomerEntity customerEntity) {
        return new CustomerResponseDto(
                new CustomerDto(customerEntity.getCustomerId(),
                        customerEntity.getFirstname(), customerEntity.getLastname(),
                        customerEntity.getEmail(),
                        customerEntity.getState(),
                        customerEntity.getCreatedAt()),
                new AddressDto(customerEntity.getAddressEntity().getAddressId(),
                        customerEntity.getAddressEntity().getStreetNum(),
                        customerEntity.getAddressEntity().getStreetName(),
                        customerEntity.getAddressEntity().getPostalCode(),
                        customerEntity.getAddressEntity().getCity(),
                        customerEntity.getAddressEntity().getCountry(),
                        customerEntity.getAddressEntity().getBirthCountry())
        );
    }

    public static CustomerEntity mapFromCustomerResponseDto(CustomerResponseDto customerResponseDto) {
        return CustomerEntity.builder()
                .customerId(customerResponseDto.customerDto().customerId())
                .firstname(customerResponseDto.customerDto().firstname())
                .lastname(customerResponseDto.customerDto().lastname())
                .email(customerResponseDto.customerDto().email())
                .state(customerResponseDto.customerDto().customerState())
                .createdAt(customerResponseDto.customerDto().createdAt())
                .addressEntity(AddressEntity.builder()
                        .addressId(customerResponseDto.addressDto().addressId())
                        .streetNum(customerResponseDto.addressDto().streetNum())
                        .streetName(customerResponseDto.addressDto().streetName())
                        .postalCode(customerResponseDto.addressDto().postalCode())
                        .city(customerResponseDto.addressDto().city())
                        .country(customerResponseDto.addressDto().country())
                        .birthCountry(customerResponseDto.addressDto().birthCountry())
                        .build())
                .build();
    }

    public static CustomerEvent mapToCustomerEventFromEntity(CustomerEntity customerEntity) {
        return CustomerEvent.newBuilder()
                .setCustomer(Customer.newBuilder()
                        .setCustomerId(customerEntity.getCustomerId())
                        .setFirstname(customerEntity.getFirstname())
                        .setLastname(customerEntity.getLastname())
                        .setEmail(customerEntity.getEmail())
                        .setCustomerState(customerEntity.getState())
                        .setCreatedAt(customerEntity.getCreatedAt())
                        .setAddress(Address.newBuilder()
                                .setAddressId(customerEntity.getAddressEntity().getAddressId())
                                .setStreetNum(customerEntity.getAddressEntity().getStreetNum())
                                .setStreetName(customerEntity.getAddressEntity().getStreetName())
                                .setPostalCode(customerEntity.getAddressEntity().getPostalCode())
                                .setCity(customerEntity.getAddressEntity().getCity())
                                .setCountry(customerEntity.getAddressEntity().getCountry())
                                .setBirthCountry(customerEntity.getAddressEntity().getBirthCountry())
                                .build())
                        .build())
                .setMessage("")
                .setStatus("")
                .build();
    }
}
