package com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.usecase;

import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.exceptions.CustomerApiBusinessException;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.exceptions.CustomerNotFoundException;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.output.EventProducer;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.output.OutputService;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.adapters.output.domain.dtos.AddressDto;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.adapters.output.domain.dtos.CustomerDto;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.adapters.output.domain.dtos.CustomerRequestDto;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.adapters.output.domain.dtos.CustomerResponseDto;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.adapters.output.domain.entities.AddressEntity;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.adapters.output.domain.entities.CustomerEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.Instant;
import java.util.Collection;
import java.util.List;

class InputServiceImplTest {
    @Mock
    private EventProducer eventProducer;
    @Mock
    private OutputService outputService;
    @InjectMocks
    private InputServiceImpl underTest;

    private AutoCloseable closeable;

    private final CustomerRequestDto customerRequestDto = new CustomerRequestDto(
            new CustomerDto("test", "test", "test", "test@domain.com", "test",
                    Instant.now()), new AddressDto("test", 1, "test", 59300,
            "test", "France", "France")
    );

    CustomerEntity customerEntity = CustomerEntity.builder()
            .customerId("1")
            .firstname("test")
            .lastname("test")
            .email("test@domain.tst")
            .state("ACTIVE")
            .createdAt(Instant.now())
            .addressEntity(AddressEntity.builder()
                    .addressId("2")
                    .streetNum(1)
                    .streetName("test")
                    .postalCode(59300)
                    .city("test")
                    .country("France")
                    .birthCountry("France")
                    .build())
            .build();

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void createCustomerTest() {
        //execute
        CustomerResponseDto responseDto = underTest.createCustomer(customerRequestDto);
        //verify
        Assertions.assertAll("*", () -> Assertions.assertNotNull(responseDto));
    }

    @Test
    void canReturnCustomerApiBusinessException_country_invalid() {
        //prepare
        final String exception = "not exists in the world";
        CustomerApiBusinessException customerApiBusinessException = Assertions
                .assertThrows(CustomerApiBusinessException.class, () -> {
                    //prepare
                    CustomerRequestDto customerRequestDto = new CustomerRequestDto(
                            new CustomerDto("test", "placide", "nduwayo", "placide.nduwayo@domain.com", "test",
                                    Instant.now()), new AddressDto("test", 1, "test", 59300,
                            "test", "INVALID COUNTRY", "France")
                    );
                    //execute
                    CustomerResponseDto customerResponseDtoNull = underTest.createCustomer(customerRequestDto);
                    //verify
                    Assertions.assertNull(customerResponseDtoNull);
                });
        //verify
        Assertions.assertAll("**", () -> {
            Assertions.assertNotNull(customerApiBusinessException);
            Assertions.assertTrue(customerApiBusinessException.getMessage().contains(exception));
        });

    }

    @Test
    void loadAllPersistedCustomers() {
        //execute
        Mockito.when(outputService.loadAllPersistedCustomers()).thenReturn(List.of());
        Collection<CustomerResponseDto> customersResponseDto = underTest.loadAllPersistedCustomers();
        //verify
        Assertions.assertAll("**", () -> {
            Assertions.assertTrue(customersResponseDto.isEmpty());
            Mockito.verify(outputService, Mockito.atLeast(1))
                    .loadAllPersistedCustomers();
        });
    }

    @Test
    void getCustomerByIdTest() {
        //prepare
        final String customerId = "1";

        //execute
        Mockito.when(outputService.getCustomerById(customerId)).thenReturn(customerEntity);
        CustomerResponseDto responseDto = underTest.getCustomerById(customerId);
        //verify
        Assertions.assertAll("**", () -> {
            Assertions.assertNotNull(responseDto);
            Mockito.verify(outputService, Mockito.atLeast(1))
                    .getCustomerById(customerId);
        });
    }

    @Test
    void getCustomerByInfoTest() {
        //prepare
        final String firstname = "placide";
        final String lastname = "nduwayo";
        final String email = "placide.nduwayo@domain.com";
        //execute
        Mockito.when(outputService.findCustomerBy(firstname, lastname, email))
                .thenReturn(customerEntity);
        CustomerResponseDto customerResponse = underTest.getCustomerBy(firstname, lastname, email);
        //verify
        Assertions.assertAll("**", () -> {
            Assertions.assertNotNull(customerResponse);
            Assertions.assertEquals(customerEntity.getFirstname(), customerResponse.customerDto().firstname());
            Mockito.verify(outputService, Mockito.atLeast(1))
                    .findCustomerBy(firstname, lastname, email);
        });
    }

    @Test
    void archiveCustomerTest() {
        //prepare
        final String customerId = "1";
        customerEntity.setState("SUSPENDED");
        //execute
        Mockito.when(outputService.getCustomerById(customerId)).thenReturn(customerEntity);
        CustomerResponseDto customerResponse = underTest.archiveCustomer(customerId);
        //verify
        Assertions.assertAll("**", () -> {
            Assertions.assertNotNull(customerResponse);
            Mockito.verify(outputService, Mockito.atLeast(1))
                    .getCustomerById(customerId);
        });
    }

    @Test
    void archiveCustomerCouldReturnExceptionTestWhenCustomerDoesNotExist() {
        //prepare
        final String exceptionPart = "customer with id bellow";
        CustomerNotFoundException customerNotFoundException = Assertions
                .assertThrows(CustomerNotFoundException.class, () -> {
                    //prepare
                    final String customerId = "1";
                    //execute
                    Mockito.when(outputService.getCustomerById(customerId))
                            .thenReturn(null);
                    CustomerResponseDto customerResponse = underTest.archiveCustomer(customerId);
                    //verify
                    Assertions.assertAll("**", () -> {
                        Mockito.verify(outputService, Mockito.atLeast(1))
                                .getCustomerById(customerId);
                        Assertions.assertNull(customerResponse);
                    });
                });
        Assertions.assertAll("**", () -> {
            Assertions.assertNotNull(customerNotFoundException);
            Assertions.assertTrue(customerNotFoundException.getMessage().contains(exceptionPart));
        });
    }

    @Test
    void archiveCustomerCouldReturnExceptionTestWhenCustomerAlreadyArchived() {
        //prepare
        final String exceptionMessage = "is already archived";
        CustomerApiBusinessException customerApiBusinessException = Assertions
                .assertThrows(CustomerApiBusinessException.class, () -> {
                    //prepare
                    final String customerId = "1";
                    CustomerEntity customer = customerEntity;
                    customer.setState("ARCHIVE");
                    //execute
                    Mockito.when(outputService.getCustomerById(customerId))
                            .thenReturn(customer);
                    CustomerResponseDto customerResponse = underTest.archiveCustomer(customerId);
                    Assertions.assertAll("**", () -> {
                        Mockito.verify(outputService, Mockito.atLeast(1))
                                .getCustomerById(customerId);
                        Assertions.assertNull(customerResponse);
                    });
                });
        Assertions.assertAll("**", () -> {
            Assertions.assertNotNull(customerApiBusinessException);
            Assertions.assertTrue(customerApiBusinessException.getMessage().contains(exceptionMessage));
        });
    }

    @Test
    void archiveCustomerCouldReturnExceptionWhenCustomerIsNotSuspended() {
        //prepare
        final String exceptionMessage = "he must be suspended";
        CustomerApiBusinessException apiBusinessException = Assertions
                .assertThrows(CustomerApiBusinessException.class, () -> {
                    //prepare
                    final String customerId = "1";
                    CustomerEntity customer = customerEntity;
                    //execute
                    Mockito.when(outputService.getCustomerById(customerId))
                            .thenReturn(customer);
                    CustomerResponseDto customerResponse = underTest.archiveCustomer(customerId);
                    //verify
                    Assertions.assertAll("**", () -> {
                        Mockito.verify(outputService, Mockito.atLeast(1))
                                .getCustomerById(customerId);
                        Assertions.assertNull(customerResponse);
                    });
                });
        Assertions.assertAll("**", () -> {
            Assertions.assertNotNull(apiBusinessException);
            Assertions.assertTrue(apiBusinessException.getMessage().contains(exceptionMessage));
        });
    }

    @Test
    void updateCustomerInfoTest() {
        //prepare
        final String customerId = "1";
        CustomerRequestDto customerRequestDto = new CustomerRequestDto(
                new CustomerDto("test", "Placide", "Nduwayo", "placide.nduwayo@domain.com", "test",
                        Instant.now()), new AddressDto("test", 1, "test", 59300,
                "test", "France", "France")
        );
        //execute
        Mockito.when(outputService.getCustomerById(customerId)).thenReturn(customerEntity);
        CustomerResponseDto responseDto = underTest.updateCustomerInfo(customerId, customerRequestDto);
        //verify
        Assertions.assertAll("**", () -> {
            Mockito.verify(outputService, Mockito.atLeast(1))
                    .getCustomerById(customerId);
            Assertions.assertNotNull(responseDto);
        });
    }

    @Test
    void customerSwitchStateFromActiveToSuspendedTest() {
        //prepare
        final String customerId = "1";
        //execute
        Mockito.when(outputService.getCustomerById(customerId)).thenReturn(customerEntity);
        CustomerResponseDto responseDto = underTest.customerSwitchState(customerId);

        //verify
        Assertions.assertAll("**", () -> {
            Mockito.verify(outputService, Mockito.atLeast(1))
                    .getCustomerById(customerId);
            Assertions.assertNotNull(responseDto);
        });
    }

    @Test
    void customerSwitchStateFromSuspendedToActiveTest() {
        //prepare
        final String customerId = "1";
        CustomerEntity customer = customerEntity;
        customer.setState("SUSPENDED");
        //execute
        Mockito.when(outputService.getCustomerById(customerId)).thenReturn(customer);
        CustomerResponseDto responseDto = underTest.customerSwitchState(customerId);

        //verify
        Assertions.assertAll("**", () -> {
            Mockito.verify(outputService, Mockito.atLeast(1))
                    .getCustomerById(customerId);
            Assertions.assertNotNull(responseDto);
            Assertions.assertEquals("ACTIVE", responseDto.customerDto().customerState());
        });
    }

    @Test
    void customerSwitchStateCouldReturnExceptionWhenCustomerDoesNotExist() {
        //prepare
        final String exceptionPart = "is not found";
        CustomerNotFoundException customerNotFoundException = Assertions
                .assertThrows(CustomerNotFoundException.class, () -> {
                    final String customerId = "1";
                    Mockito.when(outputService.getCustomerById(customerId))
                            .thenReturn(null);
                    CustomerResponseDto customerResponse = underTest.customerSwitchState(customerId);
                    Assertions.assertAll("**", () -> {
                        Mockito.verify(outputService, Mockito.atLeast(1))
                                .getCustomerById(customerId);
                        Assertions.assertNull(customerResponse);
                    });
                });
        Assertions.assertAll("**", () -> {
            Assertions.assertNotNull(customerNotFoundException);
            Assertions.assertTrue(customerNotFoundException.getMessage().contains(exceptionPart));
        });
    }

    @Test
    void customerSwitchStateCouldReturnExceptionWhenCustomerAlreadyArchived() {
        //prepare
        final String exception = "customer is archived";
        RuntimeException runtimeException = Assertions
                .assertThrows(CustomerApiBusinessException.class, () -> {
                    //prepare
                    final String customerId = "1";
                    CustomerEntity customer = customerEntity;
                    customer.setState("ARCHIVE");
                    //execute
                    Mockito.when(outputService.getCustomerById(customerId)).thenReturn(customer);
                    CustomerResponseDto customerResponse = underTest.customerSwitchState(customerId);
                    Assertions.assertAll("**", () -> {
                        Mockito.verify(outputService, Mockito.atLeast(1))
                                .getCustomerById(customerId);
                        Assertions.assertNull(customerResponse);
                    });
                });
        Assertions.assertAll("**", () -> {
            Assertions.assertNotNull(runtimeException);
            Assertions.assertEquals(exception, runtimeException.getMessage());
        });
    }

    @Test
    void updateCustomerInfoCouldReturnExceptionWhenCustomerIsNotFound() {
        final String exceptionPart = "not found";
        RuntimeException runtimeException = Assertions
                .assertThrows(CustomerNotFoundException.class, () -> {
                    final String customerId = "1";
                    Mockito.when(outputService.getCustomerById(customerId))
                            .thenReturn(null);
                    CustomerResponseDto customerResponseDto = underTest.updateCustomerInfo(customerId, customerRequestDto);
                    Assertions.assertAll("**", () -> {
                        Mockito.verify(outputService, Mockito.atLeast(1))
                                .getCustomerById(customerId);
                        Assertions.assertNull(customerResponseDto);
                    });
                });
        Assertions.assertAll("**", () -> {
            Assertions.assertNotNull(runtimeException);
            Assertions.assertTrue(runtimeException.getMessage().contains(exceptionPart));
        });
    }

    @Test
    void updateCustomerInfoCouldReturnExceptionWhenCustomerAlreadyArchived() {
        //prepare
        final String exceptionPart = "because of its state";
        RuntimeException runtimeException = Assertions
                .assertThrows(CustomerApiBusinessException.class, () -> {
                    final String customerId = "1";
                    CustomerEntity customer = customerEntity;
                    customer.setState("ARCHIVE");

                    Mockito.when(outputService.getCustomerById(customerId))
                            .thenReturn(customer);
                    CustomerResponseDto customerResponse = underTest.updateCustomerInfo(customerId, customerRequestDto);
                    Assertions.assertAll("**", () -> {
                        Mockito.verify(outputService, Mockito.atLeast(1))
                                .getCustomerById(customerId);
                        Assertions.assertNull(customerResponse);
                    });
                });
        Assertions.assertAll("**", () -> {
            Assertions.assertNotNull(runtimeException);
            Assertions.assertTrue(runtimeException.getMessage().contains(exceptionPart));
        });
    }

    @Test
    void createCustomerCouldReturnExceptionWhenCustomerAlreadyExists() {
        //prepare
        final String exceptionPart = "customer input information";
        RuntimeException runtimeException = Assertions
                .assertThrows(CustomerApiBusinessException.class, () -> {
                    //execute
                    Mockito.when(outputService.findCustomerBy(customerRequestDto.customerDto().firstname(),
                                    customerRequestDto.customerDto().lastname(), customerRequestDto.customerDto().email()))
                            .thenReturn(customerEntity);
                    CustomerResponseDto customerResponse = underTest.createCustomer(customerRequestDto);
                    Assertions.assertAll("**", () -> {
                        Mockito.verify(outputService, Mockito.atLeast(1))
                                .findCustomerBy(customerRequestDto.customerDto().firstname(),
                                        customerRequestDto.customerDto().lastname(), customerRequestDto.customerDto().email());
                        Assertions.assertNull(customerResponse);
                    });
                });
        //verify
        Assertions.assertAll("**", () -> {
            Assertions.assertNotNull(runtimeException);
            Assertions.assertTrue(runtimeException.getMessage().contains(exceptionPart));
        });
    }
}