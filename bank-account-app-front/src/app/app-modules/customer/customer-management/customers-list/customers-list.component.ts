import { Component, inject, Input, OnInit } from '@angular/core';
import { TableModule } from 'primeng/table'
import { CustomerResponseDto } from '../../../../shared/models/backend/customer/customer-response.dto';
import { CommonModule } from '@angular/common';
import { ButtonModule } from 'primeng/button';
import { TooltipModule } from 'primeng/tooltip';
import { CustomerEvent } from '../../../../shared/models/events/customer-action.events';
import { CustomerEventsService } from '../../../../shared/services/user-events/customer/customer-events.service';
import { ConfirmationService, MessageService } from 'primeng/api';
import { TagModule } from 'primeng/tag';
import { ToastModule } from 'primeng/toast';
import {
  acceptLabel,
  confirmMsg,
  errorDetailsMessage,
  errorSeverity,
  life,
  observableComplete,
  rejectedDetails,
  rejectLabel,
  successDetailsMessage,
  successSeverity
} from '../../../../shared/const-variables/const-variables';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { CustomerService } from '../../../../shared/services/backend/customer/customer.service';
import { MessagesModule } from 'primeng/messages';
import { SidebarModule } from 'primeng/sidebar';
import { CardModule } from 'primeng/card';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { DropdownModule } from 'primeng/dropdown';
import { InputTextModule } from 'primeng/inputtext';
import { AccountEventsService } from '../../../../shared/services/user-events/account/account-events.service';
import { AccountEvent } from '../../../../shared/models/events/account-action.events';
import { AccountService } from '../../../../shared/services/backend/account/account.service';
import { AccountResponseDto } from '../../../../shared/models/backend/account/account-response.dto';
import { AccountRequestDto } from '../../../../shared/models/backend/account/account-request.dto';
import { SplitterModule } from 'primeng/splitter';
import { CustomerRequestDto } from '../../../../shared/models/backend/customer/customer-request.dto';

interface AccountType {
  type: string;
}

@Component({
  selector: 'app-customers-list',
  standalone: true,
  imports: [TableModule, CommonModule, ButtonModule, TooltipModule, ConfirmDialogModule, MessagesModule, TagModule,
    ToastModule, SidebarModule, CardModule, FormsModule, DropdownModule, ReactiveFormsModule, InputTextModule,
    SplitterModule
  ],
  providers: [ConfirmationService, MessageService],
  templateUrl: './customers-list.component.html',
  styleUrl: './customers-list.component.css'
})
export class CustomersListComponent implements OnInit {

  @Input() customers!: Array<CustomerResponseDto>;

  selectedCustomer!: CustomerResponseDto;
  customerEventService = inject(CustomerEventsService);

  confirmationService = inject(ConfirmationService);
  messageService = inject(MessageService);

  customerService = inject(CustomerService);

  accountSidebarVisible: boolean = false;
  sidebarPosition: string = "left";
  customerSidebarVisible: boolean = false;

  accountFormGroup!: FormGroup;
  formBuilder = inject(FormBuilder);
  accountTypes: AccountType[] | undefined;

  accountEventService = inject(AccountEventsService);
  accountService = inject(AccountService);

  customerRequestDtoFormGroup!: FormGroup;
  private minLen: number = 2;
  private min: number = 1;

  tooltipPosition: string = "top";


  ngOnInit(): void {

    // subscribe on all customer events published 
    this.customerEventService.customerEventObservable.subscribe({
      next: (event: CustomerEvent) => {
        if (event === CustomerEvent.SWITCH_CUSTOMER_STATE_EVENT) {
          console.log(event);
          this.confirmationService.confirm({
            acceptLabel: acceptLabel,
            rejectLabel: rejectLabel,
            message: confirmMsg,
            accept: () => {
              this.customerService.customerSwitchState(this.selectedCustomer.customerDto.customerId).subscribe({
                next: (customer) => {
                  this.messageService.add({
                    key: "key1",
                    severity: successSeverity,
                    detail: successDetailsMessage,
                    life: life
                  });
                  this.customerEventService.publish(CustomerEvent.REFRESH_EVENT);
                  return customer;
                },
                error: () => {
                  this.messageService.add({
                    key: "key2",
                    severity: errorSeverity,
                    detail: errorDetailsMessage,
                    life: life
                  });
                  return null;
                },
                complete: () => {
                  console.log(observableComplete);
                }
              });
            },
            reject: () => {
              this.messageService.add({
                key: "key2",
                severity: errorSeverity,
                detail: rejectedDetails,
                life: life
              });
            }
          });
        }

        else if (event === CustomerEvent.UPDATE_CUSTOMER_EVENT) {
          console.log(event);
          this.confirmationService.confirm({
            acceptLabel: acceptLabel,
            rejectLabel: rejectLabel,
            message: confirmMsg,
            accept: () => {
              let customerRequestDto: CustomerRequestDto = new CustomerRequestDto();
              customerRequestDto.addressDto = this.customerRequestDtoFormGroup.value.address;
              customerRequestDto.customerDto = this.customerRequestDtoFormGroup.value.customer;
              customerRequestDto.addressDto.country = "France"; //country unchangeable on backend
              customerRequestDto.addressDto.birthCountry = "FRance"; //birthCountry unchangeable on backend
              const customerId: string = this.customerRequestDtoFormGroup.controls['customer'].get('customerId')?.value;
              this.customerService.editCustomer(customerId, customerRequestDto).subscribe({
                next: (response: CustomerResponseDto) => {
                  this.messageService.add({
                    key: "key1",
                    severity: successSeverity,
                    detail: successDetailsMessage,
                    life: life
                  });
                  this.customerEventService.publish(CustomerEvent.REFRESH_EVENT);
                  return response;
                },
                error: () => {
                  this.messageService.add({
                    key: "key2",
                    severity: errorSeverity,
                    detail: errorDetailsMessage,
                    life: life
                  });
                  return null;
                },
                complete: () => {
                  console.log(observableComplete);
                }
              });
            },
            reject: () => {
              this.messageService.add({
                key: "key2",
                severity: errorSeverity,
                detail: rejectedDetails,
                life: life
              });
            }
          });
        }
        else if (event === CustomerEvent.ARCHIVE_CUSTOMER_EVENT) {
          console.log(event);
          this.confirmationService.confirm({
            acceptLabel: acceptLabel,
            rejectLabel: rejectLabel,
            message: confirmMsg,
            accept: () => {
              this.customerService.archiveCustomer(this.selectedCustomer.customerDto.customerId)
                .subscribe({
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
                      key: "key2",
                      severity: errorSeverity,
                      detail: errorDetailsMessage,
                      life: life
                    });
                    return null;
                  },
                  complete: () => console.log(observableComplete)
                });
            },
            reject: () => {
              this.messageService.add({
                key: "key2",
                severity: errorSeverity,
                detail: rejectedDetails,
                life: life
              });
            }
          });
        }
      }
    });

    //create account request form group related to account sidebar
    this.accountTypes = [{ type: 'CURRENT' }, { type: 'SAVING' }]
    this.accountFormGroup = this.formBuilder.group({
      customerId: [{ value: '', disabled: true }],
      type: ['', Validators.required],
      balance: ['', [Validators.required, Validators.min(50)]]
    });

    // subscribe on all account events published
    this.accountEventService.accountEventObservable.subscribe((event: AccountEvent) => {
      if (event === AccountEvent.CREATE_ACCOUNT_EVENT) {
        console.log(event);
        this.confirmationService.confirm({
          acceptLabel: acceptLabel,
          rejectLabel: rejectLabel,
          message: confirmMsg,
          accept: () => {
            let accountRequestDto: AccountRequestDto = this.accountFormGroup.value;
            accountRequestDto.customerId = this.accountFormGroup.get("customerId")?.value;
            accountRequestDto.type = this.accountFormGroup.get('type')?.value.type;
            this.accountService.createAccount(accountRequestDto).subscribe({
              next: (account: AccountResponseDto) => {
                this.messageService.add({
                  key: "key1",
                  severity: successSeverity,
                  detail: successDetailsMessage,
                  life: life
                });
                this.accountEventService.publish(AccountEvent.REFRESH_EVENT);
                return account;
              }, error: () => {
                this.messageService.add({
                  key: "key2",
                  severity: errorSeverity,
                  detail: errorDetailsMessage,
                  life: life
                });
                return null;
              },
              complete: () => {
                console.log(observableComplete);
              }
            });
          },
          reject: () => {
            this.messageService.add({
              key: "key2",
              severity: errorSeverity,
              detail: rejectedDetails,
              life: life
            });
          }
        });
      }
    });

    //create customer request form group related to customer sidebar for updating a customer
    this.customerRequestDtoFormGroup = this.formBuilder.group({
      customer: this.formBuilder.group({
        customerId: [{ value: '', disabled: true }],
        firstname: ['', [Validators.required, Validators.minLength(this.minLen)]],
        lastname: ['', [Validators.required, Validators.minLength(this.minLen)]],
        email: ['', [Validators.required, Validators.minLength(4)]],
      }),
      address: this.formBuilder.group({
        streetNum: ['', [Validators.required, Validators.min(this.min)]],
        streetName: ['', [Validators.required, Validators.minLength(this.minLen)]],
        postalCode: ['', [Validators.required, Validators.min(this.min)]],
        city: ['', [Validators.required, Validators.minLength(this.minLen)]]
      })
    });
  }

  //produce event on user click button suspend
  onSwitchStateCustomerEvent(customer: CustomerResponseDto) {
    this.selectedCustomer = customer;
    this.customerEventService.publish(CustomerEvent.SWITCH_CUSTOMER_STATE_EVENT)
  }

  // change state printed color base on customer state
  onCustomerStateServerity(state: string) {
    if (state === "SUSPENDED" || state==="ARCHIVE") {
      return 'font-weight: bold; color: red;';
    }
    else {
      return 'font-weight: bold; color: forestgreen;';
    }
  }

  /**when user clicks on create account button to create account for customer sidebar is opened and selected customer is initialised */
  onOpenAccountUISidebar(customer: CustomerResponseDto) {
    this.accountSidebarVisible = true;
    this.selectedCustomer = customer;
  }

  //publish event on creating account on user click on create account button
  onCreateAccountEvent() {
    this.accountEventService.publish(AccountEvent.CREATE_ACCOUNT_EVENT);
  }

  /**when user clicks on update customer button to update customer, sidebar is opened and selected customer is initialised */
  onOpenCustomerUpdateUIEvent(customer: CustomerResponseDto) {
    this.selectedCustomer = customer;
    this.customerSidebarVisible = true;
  }

  // on user click on update button, send event related
  onUpdateCustomer() {
    this.customerEventService.publish(CustomerEvent.UPDATE_CUSTOMER_EVENT);
  }


  // to back to table page where we were before going to anothe page
  first: number = Number(localStorage.getItem('customerPage'))
  onTablePageChange($event: any) {
    this.first = $event.first;
    localStorage.setItem('customerPage', this.first.toString());
  }

  // show on template the tooltip
  onGetCustomerState(customerState: string): string {
    if (customerState === "ACTIVE") {
      return "suspend";
    }
    else if (customerState === "SUSPENDED") {
      return "reactive";
    }
    return "";
  }

  onArchiveCustomerEvent(customer: CustomerResponseDto) {
    this.selectedCustomer = customer;
    this.customerEventService.publish(CustomerEvent.ARCHIVE_CUSTOMER_EVENT);
  }

  //masking archived customer
  onArchivedCustomerStyle(state: String) : string {
    if(state==="ARCHIVE") {
      return "color: #f00";
    }
    return "";
  }
}
