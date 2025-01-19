import { Injectable } from '@angular/core';
import { AccountEvent } from '../../../models/events/account-action.events';
import { Observable, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AccountEventsService {

  private accountEventSubject: Subject<AccountEvent> = new Subject<AccountEvent>();
  public accountEventObservable: Observable<AccountEvent> = this.accountEventSubject.asObservable();

  public publish(event: AccountEvent) {
    this.accountEventSubject.next(event);
  }
}
