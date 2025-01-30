import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../../../environments/environment';
import { appHeaders } from '../../../headers/app.header';
import { CustomerRequestDto } from '../../../models/backend/customer/customer-request.dto';
import { CustomerResponseDto } from '../../../models/backend/customer/customer-response.dto';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  httpClient = inject(HttpClient);
  customerBasePath: string = `${environment.gatewayApiService}/customer`;

  createNewCusomer(customerRequestDto: CustomerRequestDto): Observable<CustomerResponseDto> {
      const newCustomer: Observable<CustomerResponseDto> = this.httpClient
          .post<CustomerResponseDto>(`${this.customerBasePath}/customers`, customerRequestDto, { headers: appHeaders });
      console.log(`new customer created ${newCustomer}`);
      return newCustomer;
  }

  loadAllCustomers(): Observable<Array<CustomerResponseDto>> {
      const customers: Observable<Array<CustomerResponseDto>> = this.httpClient
          .get<Array<CustomerResponseDto>>(`${this.customerBasePath}/customers`);
      return customers;
  }

  customerSwitchState(customerId: string): Observable<CustomerResponseDto> {
      const customer: Observable<CustomerResponseDto> = this.httpClient
          .post<CustomerResponseDto>(`${this.customerBasePath}/customers/switch-state/${customerId}`, { headers: appHeaders });
      console.log(`new customer created ${customer}`);

      return customer;
  }

  editCustomer(customerId: string, customerRequestDto: CustomerRequestDto) : Observable<CustomerResponseDto>{
    const updatedCustomer: Observable<CustomerResponseDto> = this.httpClient
    .put<CustomerResponseDto>(`${this.customerBasePath}/customers/${customerId}`,customerRequestDto,{headers: appHeaders});
    return  updatedCustomer;
  }

  archiveCustomer(customerId: string) : Observable<CustomerResponseDto> {
    const customer: Observable<CustomerResponseDto> = this.httpClient
    .post<CustomerResponseDto> (`${this.customerBasePath}/customers/archive/${customerId}`,{headers: appHeaders});

    return customer;
  }
}
