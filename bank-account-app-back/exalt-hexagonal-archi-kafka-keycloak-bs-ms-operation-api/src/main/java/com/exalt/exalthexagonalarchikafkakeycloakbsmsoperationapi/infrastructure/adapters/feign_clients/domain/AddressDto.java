package com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.infrastructure.adapters.feign_clients.domain;

public record AddressDto (String addressId, int streetNum, String streetName, int postalCode,
                          String city, String country, String birthCountry) {
}
