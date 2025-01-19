import { TestBed } from '@angular/core/testing';

import { CustomerEventsService } from './customer-events.service';

describe('CustomerEventsService', () => {
  let service: CustomerEventsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CustomerEventsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
