import { Injectable } from '@angular/core';
import { CustomerEvent } from '../../../models/events/customer-action.events';
import { Subject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CustomerEventsService {

  private customerEventSubject: Subject<CustomerEvent> = new Subject<CustomerEvent>();
  public customerEventObservable: Observable<CustomerEvent> = this.customerEventSubject.asObservable();

  publish(event: CustomerEvent) {
    this.customerEventSubject.next(event);
  }
}
