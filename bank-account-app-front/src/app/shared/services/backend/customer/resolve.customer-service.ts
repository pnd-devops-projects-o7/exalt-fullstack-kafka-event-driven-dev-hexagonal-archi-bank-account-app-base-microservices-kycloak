import { ResolveFn } from "@angular/router";
import { CustomerService } from "./customer.service";
import { inject } from "@angular/core";
import { CustomerResponseDto } from "../../../models/backend/customer/customer-response.dto";

export const getAllCustomersResolveFn: ResolveFn<Array<CustomerResponseDto>> = () => {
    return inject(CustomerService).loadAllCustomers();
}