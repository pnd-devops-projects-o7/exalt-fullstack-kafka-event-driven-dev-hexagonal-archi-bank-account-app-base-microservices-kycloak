package com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.usecase;

import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.avro_beans.Customer;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.avro_beans.CustomerEvent;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.avro_beans.CustomerState;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.exceptions.*;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.output.CustomerOutputService;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.output.dtos.AddressDto;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.output.dtos.CustomerDto;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.output.dtos.CustomerRequestDto;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.output.dtos.CustomerResponseDto;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.output.entities.AddressEntity;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.output.entities.CustomerEntity;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.output.CustomerProducerEvent;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.input.InputService;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InputServiceImpl implements InputService {
    /*
     * inject hexagonal architecture output connectors:
     * CustomerProducerEvent connector: to publish customer event into  kafka topic
     * OutputService connector: is injected here to be used n persisting customer data in db
     */
    private final CustomerProducerEvent customerProducerEvent;
    private final CustomerOutputService outputService;
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    public InputServiceImpl(CustomerProducerEvent customerProducerEvent, CustomerOutputService outputService) {
        this.customerProducerEvent = customerProducerEvent;
        this.outputService = outputService;
    }

    @Override
    public Map<String, Map<String, String>> getWelcome() {
        return outputService.getWelcome();
    }

    @Override
    public CustomerResponseDto createCustomer(CustomerRequestDto customerRequestDto) {
        //validate customer input information
        validateCustomerDtoInfos(customerRequestDto);
        if (CustomerValidation.isInvalidBirthCountry(customerRequestDto.addressDto().birthCountry())) {
            throw new InputBirthCountryNotExistsException("birth country input not exists in the world");
        }
        List<String> emails = loadAllPersistedCustomers().stream()
                .map(customerResponseDto -> customerResponseDto.customerDto().email()).toList();

        if (CustomerValidation.emailAssigned(customerRequestDto.customerDto().email(), emails)) {
            throw new EmailProvidedAlreadyAssignedException("email provided already assigned");
        }
        CustomerEntity customerEntity = outputService.findCustomerBy(
                customerRequestDto.customerDto().firstname(),
                customerRequestDto.customerDto().lastname(),
                customerRequestDto.customerDto().email());

        if (CustomerValidation.customerExists(customerEntity)) {
            throw new CustomerWithSameInformationExistsException(String.format("customer input information %s,%s,%s already exists",
                            customerEntity.getFirstname(), customerEntity.getFirstname(), customerEntity.getEmail()));
        }
        Customer customer = MapperService.map(customerRequestDto);
        CustomerEvent customerEvent = CustomerEvent.newBuilder()
                .setMessage("Customer CREATE event")
                .setStatus("CREATE")
                .setCustomer(customer)
                .build();
        CustomerEntity saveCustomerEntity = MapperService.map(customerEvent.getCustomer());
        AddressEntity saveAddressEntity = saveCustomerEntity.getAddressEntity();
        logger.log(Level.INFO, "saving {0} in database customer and address", customerEvent.getCustomer());

        outputService.createCustomer(saveAddressEntity, saveCustomerEntity);
        logger.log(Level.INFO, "publishing event {0}", customerEvent);
        customerProducerEvent.createCustomerEvent(customerEvent);
        AddressDto addressDto = new AddressDto(customer.getAddress().getAddressId(),
                customer.getAddress().getStreetNum(),
                customer.getAddress().getStreetName(),
                customer.getAddress().getPostalCode(),
                customer.getAddress().getCity(),
                customer.getAddress().getCountry(),
                customer.getAddress().getBirthCountry());
        CustomerDto customerDto = new CustomerDto(customer.getCustomerId(),
                customer.getFirstname(),
                customer.getLastname(),
                customer.getEmail(),
                customer.getCustomerState(),
                customer.getCreatedAt());

        return new CustomerResponseDto(customerDto, addressDto);
    }

    @Override
    public List<CustomerResponseDto> loadAllPersistedCustomers() {
        return outputService.loadAllPersistedCustomers().stream()
                .map(MapperService::map)
                .toList();
    }

    @Override
    public CustomerResponseDto getCustomerBy(String firstname, String lastname, String email) {
        CustomerEntity customerEntity = outputService.findCustomerBy(firstname, lastname, email);
        if (customerEntity == null) {
            throw new CustomerNotFoundException(String.format("customer not found with id %s,%s,%s", firstname,lastname,email));
        }
        return MapperService.map(customerEntity);
    }

    @Override
    public CustomerResponseDto archiveCustomer(UUID customerId) {
        CustomerResponseDto customerResponseDto = getCustomerById(customerId);
        CustomerEntity customerEntity = MapperService.map(customerResponseDto);
        if (customerEntity.getState().equals(CustomerState.ARCHIVE)) {
            throw new CustomerAlreadyArchivedException("customer is already archived");
        }
        customerEntity.setState(CustomerState.ARCHIVE);
        CustomerEntity archivedCustomer = outputService.archiveCustomer(customerEntity);
        CustomerEvent customerEvent = MapperService.map2(archivedCustomer);
        customerEvent.setStatus("ARCHIVING");
        customerEvent.setMessage("Customer ARCHIVING event");
        logger.log(Level.INFO, "calling output connector to publish event");
        customerProducerEvent.customerArchiveEvent(customerEvent);
        return MapperService.map(archivedCustomer);
    }

    @Override
    public CustomerResponseDto getCustomerById(UUID customerId) {
        CustomerEntity customerEntity = outputService.getCustomerById(customerId);
        if (customerEntity == null) {
            throw new CustomerNotFoundException("customer not found");
        }
        return MapperService.map(customerEntity);
    }

    @Override
    public CustomerResponseDto updateCustomerInfo(UUID customerId, CustomerRequestDto customerRequestDto) {
        CustomerEntity customerEntity = outputService.getCustomerById(customerId);
        //validate customer input information
        validateCustomerDtoInfos(customerRequestDto);
        if (customerEntity == null) {
            throw new CustomerNotFoundException(String.format("customer not found with id %s", customerId));
        }
        if(customerEntity.getState().equals(CustomerState.ARCHIVE)){
            throw new CustomerStateArchivedException("customer can not be updated, he is archived");
        }
        customerEntity.setFirstname(customerRequestDto.customerDto().firstname());
        customerEntity.setLastname(customerRequestDto.customerDto().lastname());
        customerEntity.setEmail(customerRequestDto.customerDto().email());
        customerEntity.setAddressEntity(
                AddressEntity.builder()
                        .addressId(customerEntity.getAddressEntity().getAddressId())
                        .streetNum(customerRequestDto.addressDto().streetNum())
                        .streetName(customerRequestDto.addressDto().streetName())
                        .postalCode(customerRequestDto.addressDto().postalCode())
                        .city(customerRequestDto.addressDto().city())
                        .country(customerRequestDto.addressDto().country())
                        .birthCountry(customerEntity.getAddressEntity().getBirthCountry())
                        .build()
        );
        AddressEntity addressEntity = customerEntity.getAddressEntity();
        logger.log(Level.INFO, "call output connector to update customer into db");
        outputService.updateCustomerInfo(addressEntity, customerEntity);
        CustomerEvent customerEvent = MapperService.map2(customerEntity);
        customerEvent.setStatus("UPDATE");
        customerEvent.setMessage("Customer UPDATE event");
        logger.log(Level.INFO, "call output connector to publish event related to persisting in db");
        customerProducerEvent.updateCustomerEvent(customerEvent);

        return MapperService.map(customerEntity);
    }

    private void validateCustomerDtoInfos(CustomerRequestDto customerRequestDto){
        /*check validity of customer request dto*/
        if (CustomerValidation.isInvalidCustomerRequestDto(customerRequestDto)) {
            throw new InvalidInputFieldsException("data input invalid, please enter valid one(s)");
        }

        if (CustomerValidation.isInvalidCountry(customerRequestDto.addressDto().country())) {
            throw new InputCountryNotExistsException("country input not exists in the world");
        }

        if (CustomerValidation.isInvalidEmail(customerRequestDto.customerDto().email())) {
            throw new InvalidEmailException("email input not a valid email");
        }
    }

}
