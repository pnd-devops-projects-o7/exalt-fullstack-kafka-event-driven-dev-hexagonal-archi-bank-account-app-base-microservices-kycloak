package com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.usecase;

import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.avro_beans.Customer;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.avro_beans.CustomerEvent;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.exceptions.*;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.input.InputService;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.output.OutputService;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.output.EventProducer;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.adapters.output.domain.dtos.AddressDto;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.adapters.output.domain.dtos.CustomerDto;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.adapters.output.domain.dtos.CustomerRequestDto;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.adapters.output.domain.dtos.CustomerResponseDto;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.adapters.output.domain.entities.AddressEntity;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.infrastructure.adapters.output.domain.entities.CustomerEntity;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InputServiceImpl implements InputService {
    /*
     * inject hexagonal architecture output connectors:
     * CustomerProducerEvent connector: to publish customer event into  kafka topic
     * OutputService connector: is injected here to be used n persisting customer data in db
     */
    private final EventProducer eventProducer;
    private final OutputService outputService;
    private static final Logger LOGGER = Logger.getLogger(InputServiceImpl.class.getName());
    private static final String ARCHIVE="ARCHIVE";
    private static final String ACTIVE="ACTIVE";
    private static final String SUSPENDED="SUSPENDED";

    public InputServiceImpl(final EventProducer eventProducer, final OutputService outputService) {
        this.eventProducer = eventProducer;
        this.outputService = outputService;
    }
    @Override
    public CustomerResponseDto createCustomer(CustomerRequestDto customerRequestDto) {
        //validate customer input information
        validateCustomerDtoInfos(customerRequestDto);
        if (CustomerValidation.isInvalidBirthCountry(customerRequestDto.addressDto().birthCountry())) {
            throw new CustomerApiBusinessException("country input not exists in the world");
        }
        List<String> emails = loadAllPersistedCustomers().stream()
                .map(customerResponseDto -> customerResponseDto.customerDto().email()).toList();

        if (CustomerValidation.emailAssigned(customerRequestDto.customerDto().email(), emails)) {
            throw new CustomerApiBusinessException("email provided already assigned");
        }
        CustomerEntity customerEntity = outputService.findCustomerBy(
                customerRequestDto.customerDto().firstname(),
                customerRequestDto.customerDto().lastname(),
                customerRequestDto.customerDto().email());

        if (CustomerValidation.customerExists(customerEntity)) {
            throw new CustomerApiBusinessException(String.format("customer input information %s,%s,%s already exists",
                    customerEntity.getFirstname(), customerEntity.getFirstname(), customerEntity.getEmail()));
        }

        Customer customer = MapperService.mapFromRequestDto(customerRequestDto);
        CustomerEvent customerEvent = CustomerEvent.newBuilder()
                .setMessage("Customer CREATE event")
                .setStatus("CREATE")
                .setCustomer(customer)
                .build();
        AddressEntity addressEntity = getAddressByInfo(customerRequestDto.addressDto());
        CustomerEntity saveCustomerEntity = MapperService.mapFromCustomer(customer);
        if (addressEntity == null) {
            addressEntity = saveCustomerEntity.getAddressEntity();
            outputService.createAddress(addressEntity);
        }
        LOGGER.info("call output connector ta save data in bdd");
        saveCustomerEntity.setAddressEntity(addressEntity);
        outputService.createCustomer(saveCustomerEntity);
        LOGGER.log(Level.INFO, "publishing event {0}", customerEvent);
        eventProducer.createCustomerEvent(customerEvent);

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
                .map(MapperService::mapFromCustomerEntity)
                .toList();
    }
    @Override
    public CustomerResponseDto getCustomerBy(String firstname, String lastname, String email) {
        CustomerEntity customerEntity = outputService.findCustomerBy(firstname, lastname, email);
        if (customerEntity == null) {
            throw new CustomerNotFoundException(String.format("customer not found with id %s,%s,%s", firstname, lastname, email));
        }
        return MapperService.mapFromCustomerEntity(customerEntity);
    }

    @Override
    public CustomerResponseDto archiveCustomer(String customerId) {
        CustomerResponseDto customerResponseDto = getCustomerById(customerId);
        if(customerResponseDto==null){
            throw new CustomerNotFoundException(String.format("customer with id bellow %s not found",customerId));
        }
        CustomerEntity customerEntity = MapperService.mapFromCustomerResponseDto(customerResponseDto);
        if (customerEntity.getState().equals(ARCHIVE)) {
            throw new CustomerApiBusinessException("customer is already archived");
        }

        if(!customerEntity.getState().equals(SUSPENDED)){
            throw new CustomerApiBusinessException("before archiving customer, he must be suspended");
        }
        customerEntity.setState(ARCHIVE);
        outputService.archiveCustomer(customerEntity);
        CustomerEvent customerEvent = MapperService.mapToCustomerEventFromEntity(customerEntity);
        customerEvent.setStatus("CUSTOMER ARCHIVED");
        customerEvent.setMessage("Customer ARCHIVING event");
       //calling output connector to publish event
        eventProducer.customerArchiveEvent(customerEvent);
        return MapperService.mapFromCustomerEntity(customerEntity);
    }

    @Override
    public CustomerResponseDto customerSwitchState(String customerId) {
        CustomerResponseDto customerResponseDto  = getCustomerById(customerId);
        if(customerResponseDto==null){
            LOGGER.log(Level.WARNING,"customer with id {0} is not found", customerId);
            throw new CustomerNotFoundException(String.format("customer with id %s is not found", customerId));
        }
        CustomerEntity customerEntity = MapperService.mapFromCustomerResponseDto(customerResponseDto);
        if(customerEntity.getState().equals(ARCHIVE)){
            LOGGER.log(Level.WARNING,"customer archived {0} ", customerEntity);
            throw new CustomerApiBusinessException("customer is archived");
        }
        CustomerEvent customerEvent = MapperService.mapToCustomerEventFromEntity(customerEntity);
        switch (customerEntity.getState()){
            //call output connector to save customer with new state related
            case  SUSPENDED -> {
                customerEntity.setState(ACTIVE);
                outputService.createCustomer(customerEntity);
                customerEvent.setStatus("CUSTOMER ACTIVATE");
                customerEvent.setMessage("Customer ACTIVATED event");
            }
            case ACTIVE -> {
                customerEntity.setState(SUSPENDED);
                outputService.createCustomer(customerEntity);
                customerEvent.setStatus("CUSTOMER SUSPENDED");
                customerEvent.setMessage("Customer SUSPENDED event");
            }
            default -> LOGGER.log(Level.WARNING,"do nothing");
        }
       //calling output connector to publish event
        eventProducer.customerSuspendEvent(customerEvent);
        return MapperService.mapFromCustomerEntity(customerEntity);
    }
    @Override
    public CustomerResponseDto getCustomerById(String customerId) {
        CustomerEntity customerEntity = outputService.getCustomerById(customerId);
        if (customerEntity == null) {
            return null;
        }
        return MapperService.mapFromCustomerEntity(customerEntity);
    }
    

    @Override
    public CustomerResponseDto updateCustomerInfo(String customerId, CustomerRequestDto customerRequestDto) {
        CustomerEntity customerEntity = outputService.getCustomerById(customerId);
        //validate customer input information
       validateCustomerDtoInfos(customerRequestDto);
        if (customerEntity == null) {
            throw new CustomerNotFoundException(String.format("customer with id %s not found", customerId));
        }
        if (customerEntity.getState().equals(ARCHIVE) || customerEntity.getState().equals(SUSPENDED)) {
            throw new CustomerApiBusinessException("customer can not be updated because of its state");
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
                        .country(customerEntity.getAddressEntity().getCountry()) //country unchangeable
                        .birthCountry(customerEntity.getAddressEntity().getBirthCountry()) //birthCountry unchangeable
                        .build()
        );
        AddressEntity addressEntity = customerEntity.getAddressEntity();
        LOGGER.log(Level.INFO, "call output connector to update customer into db");
        outputService.updateCustomerInfo(addressEntity, customerEntity);
        CustomerEvent customerEvent = MapperService.mapToCustomerEventFromEntity(customerEntity);
        customerEvent.setStatus("UPDATE");
        customerEvent.setMessage("Customer UPDATE event");
        LOGGER.log(Level.INFO, "call output connector to publish event related to persisting in db");
        eventProducer.updateCustomerEvent(customerEvent);

        return MapperService.mapFromCustomerEntity(customerEntity);
    }

    @Override
    public AddressEntity getAddressByInfo(AddressDto addressDto) {
        return outputService.getAddressByInfo(addressDto);
    }

    private void validateCustomerDtoInfos(CustomerRequestDto customerRequestDto) {
        /*check validity of customer request dto*/
        if (CustomerValidation.isInvalidCustomerRequestDto(customerRequestDto)) {
            throw new CustomerApiBusinessException("data input invalid, please enter valid one(s)");
        }

        if (CustomerValidation.isInvalidCountry(customerRequestDto.addressDto().country())) {
            throw new CustomerApiBusinessException("country input not exists in the world");
        }

        if (CustomerValidation.isInvalidEmail(customerRequestDto.customerDto().email())) {
            throw new CustomerApiBusinessException("email input not a valid email");
        }
    }
}
