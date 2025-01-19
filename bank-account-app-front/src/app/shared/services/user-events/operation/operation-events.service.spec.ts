import { TestBed } from '@angular/core/testing';

import { OperationEventsService } from '../operation/operation-events.service';

describe('OperationEventsService', () => {
  let service: OperationEventsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OperationEventsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
