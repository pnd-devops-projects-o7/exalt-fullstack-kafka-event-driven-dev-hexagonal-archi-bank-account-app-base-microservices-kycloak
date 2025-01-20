import { Component, inject, Input, OnInit } from '@angular/core';
import { AccountResponseDto } from '../../../../shared/models/backend/account/account-response.dto';
import { TableModule } from 'primeng/table';
import { CommonModule } from '@angular/common';
import { ButtonModule } from 'primeng/button';
import { TooltipModule } from 'primeng/tooltip';
import { AccountEventsService } from '../../../../shared/services/user-events/account/account-events.service';
import { AccountEvent } from '../../../../shared/models/events/account-action.events';
import {
  acceptLabel, confirmMsg, errorDetailsMessage, errorSeverity, life,
  observableComplete, rejectedDetails, rejectLabel, successDetailsMessage, successSeverity
} from '../../../../shared/const-variables/const-variables';
import { ConfirmationService, MessageService } from 'primeng/api';
import { AccountService } from '../../../../shared/services/backend/account/account.service';
import { MessagesModule } from 'primeng/messages';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { SidebarModule } from 'primeng/sidebar';
import { CardModule } from 'primeng/card';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { InputTextModule } from 'primeng/inputtext';
import { DropdownModule } from 'primeng/dropdown';
import { OperationEventsService } from '../../../../shared/services/user-events/operation/operation-events.service';
import { OperationEvent } from '../../../../shared/models/events/operation-action.events';
import { OperationRequestDto } from '../../../../shared/models/backend/operation/operation-request.dto';
import { OperationService } from '../../../../shared/services/backend/operation/operation.service';
import { OperationResponseDto } from '../../../../shared/models/backend/operation/operation-response.dto';
import { InputTextareaModule } from 'primeng/inputtextarea';
import { TransferRequestDto } from '../../../../shared/models/backend/operation/transfer-request.dto';
import { TransferResponseDto } from '../../../../shared/models/backend/operation/transfer-response.dto';
import { TransferService } from '../../../../shared/services/backend/operation/transfer.service';


interface OperationType {
  operationType: string;
}

@Component({
  selector: 'app-accounts-list',
  standalone: true,
  imports: [TableModule, CommonModule, ButtonModule, TooltipModule, MessagesModule, ConfirmDialogModule,
    SidebarModule, CardModule, FormsModule, ReactiveFormsModule, InputTextModule, DropdownModule, 
    InputTextareaModule
  ],
  providers: [ConfirmationService, MessageService],
  templateUrl: './accounts-list.component.html',
  styleUrl: './accounts-list.component.css'
})
export class AccountsListComponent implements OnInit {

  @Input() accounts!: Array<AccountResponseDto>;
  accountEventService = inject(AccountEventsService);
  operationEventService = inject(OperationEventsService);

  confirmationService = inject(ConfirmationService);
  messageService = inject(MessageService);

  accountService = inject(AccountService);
  tooltipPosition: string = "top";
  sidebarPosition: string = "left"
  accountSidebarVisible: boolean = false;

  overdraftIRateForm!: FormGroup;
  formBuilder = inject(FormBuilder);

  operationRequestDtoForm!: FormGroup;
  operationRequestDtoFormVisible!: boolean;

  operationTypes: OperationType[] = [{ operationType: 'DEPOSIT' }, { operationType: 'WITHDRAW' }];

  operationService = inject(OperationService);
  transferService = inject(TransferService);

  style: string = "font-style: normal; font-weight: bold;";
  severity: any = "danger";

  transferRequestDtoFormVisible!: boolean;
  transferRequestDtoForm!: FormGroup;
  condition1: string = "account.accountType='CURRENT'";
  condition2: string = "account.accountType='CURRENT' AND account.accountState='ACTIVE'";

  // to back to table page where we were before going to anothe page
  first: number = Number(localStorage.getItem('accountPage'))
  onTablePageChange($event: any) {
    this.first = $event.first;
    localStorage.setItem('accountPage', this.first.toString());
  }

  ngOnInit(): void {
    // subcribe to all published account events
    this.accountEventService.accountEventObservable.subscribe({
      next: (event: AccountEvent) => {
        if (event === AccountEvent.SWICTH_ACCOUNT_STATE_EVENT) {
          console.log(event);
          this.confirmationService.confirm({
            acceptLabel: acceptLabel,
            rejectLabel: rejectLabel,
            message: confirmMsg,
            accept: () => {
              this.accountService.switchAccountState(this.selectedAccount.accountId)
                .subscribe({
                  next: (value: AccountResponseDto) => {
                    this.messageService.add({
                      key: "key1",
                      severity: successSeverity,
                      detail: successDetailsMessage,
                      life: life
                    });
                    this.accountEventService.publish(AccountEvent.REFRESH_EVENT);
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

        else if (event === AccountEvent.UPDATE_ACCOUNT_OVERDRAFT_OR_IRATE_EVENT) {
          console.log(event);
          this.confirmationService.confirm({
            acceptLabel: acceptLabel,
            rejectLabel: rejectLabel,
            message: confirmMsg,
            accept: () => {
              const accountId: string = this.overdraftIRateForm.get("accountId")?.value;
              const overdraftOrIrate: number = this.overdraftIRateForm.get("overdraftIrate")?.value;
              this.accountService.updateAccountOverdraftOrInterestRate(accountId, overdraftOrIrate)
                .subscribe({
                  next: (value: AccountResponseDto) => {
                    this.messageService.add({
                      key: "key1",
                      severity: successSeverity,
                      detail: successDetailsMessage,
                      life: life
                    });
                    this.accountEventService.publish(AccountEvent.REFRESH_EVENT);
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
      complete: () => { console.log(observableComplete); }
    });

    // form group for updating overdraft or interest rate
    this.overdraftIRateForm = this.formBuilder.group({
      accountId: [{ value: '', disabled: true }, Validators.required],
      overdraftIrate: ['', Validators.required]
    });

    // form group for operation creation
    this.operationRequestDtoForm = this.formBuilder.group({
      operationType: ['', Validators.required],
      amount: ['', Validators.required],
      description: ['', [Validators.required, Validators.minLength(5)]],
      accountId: [{ value: '', disabled: true }, Validators.required],
    });

    // subscribe to operation events
    this.operationEventService.operatonEventObservable.subscribe({
      next: (event: OperationEvent) => {
        if (event === OperationEvent.UPDATE_BALANCE_ACCOUNT_EVENT) {
          console.log(event);
          this.confirmationService.confirm({
            acceptLabel: acceptLabel,
            rejectLabel: rejectLabel,
            message: confirmMsg,
            accept: () => {
              let operationRequestDto: OperationRequestDto = this.operationRequestDtoForm.value;
              operationRequestDto.accountId = this.operationRequestDtoForm.get("accountId")?.value;
              operationRequestDto.operationType = this.operationRequestDtoForm.get("operationType")?.value.operationType;
              const transactionAmount: number = this.operationRequestDtoForm.value.amount;
              this.operationService.createOperation(operationRequestDto, transactionAmount).subscribe({
                next: (value: OperationResponseDto) => {
                  this.messageService.add({
                    key: "key1",
                    severity: successSeverity,
                    detail: successDetailsMessage,
                    life: life
                  });
                  this.operationEventService.pushish(OperationEvent.REFRESH_EVENT);
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
        else if (event === OperationEvent.CREATE_TRANSFER_EVENT) {
          console.log(event);
          this.confirmationService.confirm({
            acceptLabel: acceptLabel,
            rejectLabel: rejectLabel,
            message: confirmMsg,
            accept: () => {
              const transferRequestDto: TransferRequestDto = this.transferRequestDtoForm.value;
              transferRequestDto.originAccount = this.transferRequestDtoForm.get("originAccount")?.value;
              this.transferService.createTransfer(transferRequestDto).subscribe({
                next: (value: TransferResponseDto) => {
                  this.messageService.add({
                    key: "key1",
                    severity: successSeverity,
                    detail: successDetailsMessage,
                    life: life
                  });
                  this.operationEventService.pushish(OperationEvent.REFRESH_EVENT);
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
      },
      error: () => { },
      complete: () => console.log(observableComplete)
    });

    // form group for create transfer
    this.transferRequestDtoForm = this.formBuilder.group({
      originAccount: [{ value: '', disabled: true }, Validators.required],
      destinationAccount: ['', Validators.required],
      transferAmount: ['', [Validators.required, Validators.min(10)]],
      description: ['', [Validators.required, Validators.minLength(10)]]
    });
  }

  onSetAccountColor(color: string): string {
    switch (color) {
      case 'SAVING':
        return 'color: forestgreen;';
      case 'CURRENT':
        return 'color: #00f;';
      case 'CREATED':
        return 'color: #000;';
      case 'ACTIVE':
        return 'color: forestgreen;';
      case 'SUSPENDED':
        return 'color: #f00;';
      default:
        return 'color: #f00;';
    }
  }

  onSetOverdraftIRateIconSeverity(accountType: string): any {
    if (accountType === "SAVING") {
      return "success"
    }
    else {
      return "secondary"
    }
  }

  //a corresponding state tooltip to show
  onGetAccountState(accountState: string): string {
    if (accountState === "ACTIVE") {
      return "suspend";
    }
    else if (accountState === "SUSPENDED") {
      return "reactive";
    }
    return "";
  }

  // produce event on user click button switch state account
  selectedAccount!: AccountResponseDto;

  onSwitchAccountStateEvent(account: AccountResponseDto) {
    this.selectedAccount = account;
    this.accountEventService.publish(AccountEvent.SWICTH_ACCOUNT_STATE_EVENT);
  }

  //initialise selected account and make sidebar visible
  tooltip!: string;
  onOpenOverdraftOrInterestRateUpdateSidebarUI(account: AccountResponseDto) {
    this.selectedAccount = account;
    this.accountSidebarVisible = true;
    // label to print according account type
    if (account.accountType === "SAVING") {
      this.tooltip = "update i-rate";
    }
    else if (account.accountType === "CURRENT") {
      this.tooltip = "update overdraft";
    }

  }

  // produce event on user click on button
  onUpdateAccountOverdraftOrInterestRateEvent() {
    this.accountEventService.publish(AccountEvent.UPDATE_ACCOUNT_OVERDRAFT_OR_IRATE_EVENT);
  }
  //initialise selected account and make sidebar visible
  onOpenUpdateBalanceFormUI(accout: AccountResponseDto) {
    this.selectedAccount = accout;
    this.operationRequestDtoFormVisible = true;
  }
  // produce event on user click on button
  onUpdateBalanceCurrentAccountEvent() {
    this.operationEventService.pushish(OperationEvent.UPDATE_BALANCE_ACCOUNT_EVENT);
  }

  onGetTooltip(accountType: string): string {
    if (accountType === "SAVING") {
      return "update i-rate";
    }
    else if (accountType === "CURRENT") {
      return "update overdraft";
    }

    else {
      return "";
    }
  }

  //initialise selected account as origin and make sidebar visible
  onOpenCreateTransferFromAccountFormUI(account: AccountResponseDto) {
    this.selectedAccount = account;
    this.transferRequestDtoFormVisible = true;
  }

  //on user click button create transfer, an event is published
  onCreateTransferEvent() {
    this.operationEventService.pushish(OperationEvent.CREATE_TRANSFER_EVENT);
  }

  //highlight according to account balance mount
  onBalanceAmountColoring(amount: number): string {
    if (amount <= 100) {
      return "color: red;";
    }
    else if(((amount >= 101) && (amount <= 500))){
      return "color: chocolate;";
    }
    else if(((amount >= 501) && (amount <= 1000))){
      return "cornflowerblue";
    }
    else {
      return "color:forestgreen;";
    }
  }
}
