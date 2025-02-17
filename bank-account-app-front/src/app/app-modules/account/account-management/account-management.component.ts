import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AccountResponseDto } from '../../../shared/models/backend/account/account-response.dto';
import { observableComplete } from '../../../shared/const-variables/const-variables';
import { AccountsListComponent } from './accounts-list/accounts-list.component';
import { AccountEventsService } from '../../../shared/services/user-events/account/account-events.service';
import { AccountEvent } from '../../../shared/models/events/account-action.events';
import { AccountService } from '../../../shared/services/backend/account/account.service';

@Component({
  selector: 'app-account-management',
  standalone: true,
  imports: [AccountsListComponent],
  templateUrl: './account-management.component.html',
  styleUrl: './account-management.component.css'
})
export class AccountManagementComponent implements OnInit {
  
  activatedRoute = inject(ActivatedRoute);
  accounts! : Array<AccountResponseDto>;
  accountEventService = inject(AccountEventsService);
  accountService = inject(AccountService);

  ngOnInit(): void {
    //load all accounts activated route
    this.activatedRoute.data.subscribe({
      next: ({allAccounts})=>{
        this.accounts = allAccounts;
      },
      error: ()=>{

      },
      complete : ()=>{
        console.log(observableComplete);
      }
    });

    // subscribe to all accounts events
    this.accountEventService.accountEventObservable.subscribe({
      next: (value: AccountEvent)=>{
        if(value===AccountEvent.REFRESH_EVENT) {
          this.accountService.loadAllAccounts().subscribe({
            next: (value: Array<AccountResponseDto>) => {
              this.accounts = value;
            },
            error: ()=>{

            },
            complete : ()=>{
              console.log(observableComplete);
            }
          })
        }
      },
      error: ()=>{},
      complete: ()=>console.log(observableComplete)
    });
  }

}
