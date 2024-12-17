package com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.output;

import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.avro_beans.CustomerEvent;

public interface CustomerProducerEvent {
    void createCustomerEvent(CustomerEvent customerEvent);
    void updateCustomerEvent(CustomerEvent customerEvent);
    void customerArchiveEvent(CustomerEvent customerEvent);
}
