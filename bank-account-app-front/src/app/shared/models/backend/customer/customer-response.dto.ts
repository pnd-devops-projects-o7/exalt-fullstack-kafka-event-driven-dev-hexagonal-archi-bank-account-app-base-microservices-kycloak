import { AddressDto } from "./address.dto";
import { CustomerDto } from "./customer.dto";

export class CustomerResponseDto {
    customerDto!: CustomerDto;
    addressDto!: AddressDto;
}