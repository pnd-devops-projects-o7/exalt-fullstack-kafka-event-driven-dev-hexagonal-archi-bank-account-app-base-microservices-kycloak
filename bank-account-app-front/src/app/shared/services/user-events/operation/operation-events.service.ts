import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { OperationEvent } from '../../../models/events/operation-action.events';

@Injectable({
  providedIn: 'root'
})
export class OperationEventsService {

  private operatonEventSubject : Subject<OperationEvent> = new Subject();
  public operatonEventObservable: Observable<OperationEvent> = this.operatonEventSubject.asObservable();

  pushish(event: OperationEvent) {
    this.operatonEventSubject.next(event);
  }
}
