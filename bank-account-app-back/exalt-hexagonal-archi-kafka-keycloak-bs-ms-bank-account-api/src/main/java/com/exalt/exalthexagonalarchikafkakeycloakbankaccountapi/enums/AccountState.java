package com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.enums;

import lombok.Getter;

@Getter
public enum AccountState {
    CREATED("CREATED"),
    ACTIVE("ACTIVE"),
    SUSPENDED("SUSPENDED");
    final String state;
    AccountState(String state) {
        this.state = state;
    }

}
