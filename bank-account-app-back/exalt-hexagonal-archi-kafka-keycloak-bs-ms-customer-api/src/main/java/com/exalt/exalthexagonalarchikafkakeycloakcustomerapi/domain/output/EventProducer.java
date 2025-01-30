package com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.output;

import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.avro_beans.CustomerEvent;

public interface EventProducer {
    void createCustomerEvent(CustomerEvent customerEvent);
    void updateCustomerEvent(CustomerEvent customerEvent);
    void customerArchiveEvent(CustomerEvent customerEvent);

    void customerSuspendEvent(CustomerEvent customerEvent);
}
