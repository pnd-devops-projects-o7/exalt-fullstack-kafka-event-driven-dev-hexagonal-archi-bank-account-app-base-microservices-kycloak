import { AddressDto } from "./address.dto";
import { CustomerDto } from "./customer.dto";

export class CustomerRequestDto{
    customerDto!: CustomerDto;
    addressDto!: AddressDto;
}