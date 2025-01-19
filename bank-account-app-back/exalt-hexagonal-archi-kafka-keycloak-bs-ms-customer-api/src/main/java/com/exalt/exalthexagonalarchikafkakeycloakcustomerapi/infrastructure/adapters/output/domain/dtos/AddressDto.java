package com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.adapters.output.domain.dtos;

public record AddressDto(String addressId, int streetNum, String streetName, int postalCode,
                         String city, String country, String birthCountry) {

}