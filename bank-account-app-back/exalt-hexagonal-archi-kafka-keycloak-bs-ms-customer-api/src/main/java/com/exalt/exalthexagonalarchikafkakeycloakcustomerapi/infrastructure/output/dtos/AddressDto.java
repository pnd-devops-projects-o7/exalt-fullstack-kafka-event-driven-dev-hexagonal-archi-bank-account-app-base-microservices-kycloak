package com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.output.dtos;

import java.util.UUID;

public record AddressDto(UUID addressId, int streetNum, String streetName, int postalCode, String city, String country, String birthCountry) {

}