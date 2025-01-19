package com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.infrastructure.adapters.feign_client.domain;


public record AddressDto(String addressId, int streetNum, String streetName, int postalCode, String city,
                         String country, String birthCountry) {

}