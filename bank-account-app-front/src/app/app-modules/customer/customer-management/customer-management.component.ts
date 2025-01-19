import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CustomerResponseDto } from '../../../shared/models/backend/customer/customer-response.dto';
import { CustomersListComponent } from './customers-list/customers-list.component';
import { TableModule } from 'primeng/table';
import { CustomerService } from '../../../shared/services/backend/customer/customer.service';
import { CustomerEvent } from '../../../shared/models/events/customer-action.events';
import { CustomerEventsService } from '../../../shared/services/user-events/customer/customer-events.service';

@Component({
  selector: 'app-customer-management',
  standalone: true,
  imports: [CustomersListComponent, TableModule],
  templateUrl: './customer-management.component.html',
  styleUrl: './customer-management.component.css'
})
export class CustomerManagementComponent implements OnInit {
  activatedRoute = inject(ActivatedRoute);
  customerResponseDtos!: Array<CustomerResponseDto>;
  customerEventService = inject(CustomerEventsService);
  customerService = inject(CustomerService);

  ngOnInit(): void {

    this.activatedRoute.data.subscribe({
      next: ({ all }) => {
        this.customerResponseDtos = all;
      }
    });
    this.customerEventService.customerEventObservable.subscribe((event)=>{
      switch(event) {
        case CustomerEvent.REFRESH_EVENT:
          this.customerService.loadAllCustomers().subscribe(data=>{
            this.customerResponseDtos = data;
          });
      }
    });
  }
}
