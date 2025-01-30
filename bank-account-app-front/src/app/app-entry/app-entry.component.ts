import { Component, inject, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ConfirmationService, MenuItem, MessageService, PrimeIcons } from 'primeng/api';
import { FormGroup, FormBuilder, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MenubarModule } from 'primeng/menubar';
import { SidebarModule } from 'primeng/sidebar';
import { CardModule } from 'primeng/card';
import { ButtonModule } from 'primeng/button';
import { SplitterModule } from 'primeng/splitter';
import { InputTextModule } from 'primeng/inputtext';
import { ToastModule } from 'primeng/toast';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { MessagesModule } from 'primeng/messages';
import { CustomerEvent } from '../shared/models/events/customer-action.events';
import { CustomerService } from '../shared/services/backend/customer/customer.service';
import { acceptLabel, confirmMsg, errorDetailsMessage, errorSeverity, life, rejectLabel, successDetailsMessage, successSeverity } from '../shared/const-variables/const-variables';
import { CustomerRequestDto } from '../shared/models/backend/customer/customer-request.dto';
import { CustomerResponseDto } from '../shared/models/backend/customer/customer-response.dto';
import { CustomerEventsService } from '../shared/services/user-events/customer/customer-events.service';
@Component({
  selector: 'app-app-entry',
  standalone: true,
  imports: [MenubarModule, SidebarModule, CardModule, ButtonModule, SplitterModule,
    FormsModule, ReactiveFormsModule, InputTextModule, ToastModule, ConfirmDialogModule, MessagesModule
  ],
  providers: [ConfirmationService, MessageService],
  templateUrl: './app-entry.component.html',
  styleUrl: './app-entry.component.css'
})
export class AppEntryComponent implements OnInit {

  appMenu!: MenuItem[];
  router = inject(Router)
  customerSidebarVisible: boolean = false;
  customerEventService = inject(CustomerEventsService);
  sidebarsPosition: string = "left";

  customerRequestDtoFormGroup!: FormGroup;
  fb = inject(FormBuilder);
  private minLen: number = 2;
  private min: number = 1;

  messageService = inject(MessageService);
  confirmMessageService = inject(ConfirmationService);
  customerService = inject(CustomerService);

  ngOnInit(): void {

    this.appMenu = [
      {
        label: "customer", icon: PrimeIcons.ANGLE_DOUBLE_DOWN,
        items: [
          {
            icon: PrimeIcons.LIST_CHECK, label: "customers",
            command: () => {
              this.router.navigate(["app-menu/customers"]);
            }
          },
          {
            icon: PrimeIcons.PLUS, label: "new customer",
            command: () => {
              this.customerSidebarVisible = true;
            }
          }
        ]
      },
      {
        label: "account", icon: PrimeIcons.ANGLE_DOUBLE_DOWN,
        items: [
          {
            label: 'accounts', icon: PrimeIcons.LIST_CHECK,
            command: () => this.router.navigate(["app-menu/accounts"])
          }

        ]
      },
      {
        label: "operation", icon: PrimeIcons.ANGLE_DOUBLE_DOWN,
        items: [
          {
            label: "operations", icon: PrimeIcons.LIST_CHECK,
            command: () => {
              this.router.navigate(['app-menu/operations']);
            }
          },{
            label: "transfers", icon: PrimeIcons.LIST_CHECK,
            command: ()=>this.router.navigate(["app-menu/operations/transfers"])
          }
        ]
      }
    ];

    //build customer form
    this.customerRequestDtoFormGroup = this.fb.group({
      customer: this.fb.group({
        firstname: ['', [Validators.required, Validators.minLength(this.minLen)]],
        lastname: ['', [Validators.required, Validators.minLength(this.minLen)]],
        email: ['', [Validators.required, Validators.minLength(4)]],
      }),
      address: this.fb.group({
        streetNum: ['', [Validators.required, Validators.min(this.min)]],
        streetName: ['', [Validators.required, Validators.minLength(this.minLen)]],
        postalCode: ['', [Validators.required, Validators.min(this.min)]],
        city: ['', [Validators.required, Validators.minLength(this.minLen)]],
        country: ['', [Validators.required, Validators.minLength(this.minLen)]],
        birthCountry: ['', [Validators.required, Validators.minLength(this.minLen)]]
      })
    });

    // scanning user click events about customer management
    this.customerEventService.customerEventObservable.subscribe({
      next: (event: CustomerEvent) => {
        switch (event) {
          case CustomerEvent.CREATE_NEW_CUSTOMER_EVENT:
            console.log(`create event ${event}`);
            this.confirmMessageService.confirm({
              acceptLabel: acceptLabel,
              rejectLabel: rejectLabel,
              message: confirmMsg,
              accept: () => {
                const customerRuestDto: CustomerRequestDto = new CustomerRequestDto();
                customerRuestDto.customerDto = this.customerRequestDtoFormGroup.value.customer;
                customerRuestDto.addressDto = this.customerRequestDtoFormGroup.value.address;
                this.customerService.createNewCusomer(customerRuestDto).subscribe({
                  next: (value: CustomerResponseDto) => {
                    this.messageService.add({
                      key: "key1",
                      severity: successSeverity,
                      detail: successDetailsMessage,
                      life: life
                    });
                    this.customerEventService.publish(CustomerEvent.REFRESH_EVENT);
                    return value;
                  },
                  error: () => {
                    this.messageService.add({
                      key: 'key2',
                      severity: errorSeverity,
                      detail: errorDetailsMessage,
                      life: life
                    });
                    return null;
                  }
                });
              },
              reject: () => {
                this.messageService.add({
                  key: "key2",
                  severity: errorSeverity,
                  detail: errorDetailsMessage,
                  life: life
                });
                return null;
              }
            });
        }
      }
    });
  }

  //create new customer sidebar form
  createNewCustomerEvent() {
    this.customerEventService.publish(CustomerEvent.CREATE_NEW_CUSTOMER_EVENT);
  }

}
