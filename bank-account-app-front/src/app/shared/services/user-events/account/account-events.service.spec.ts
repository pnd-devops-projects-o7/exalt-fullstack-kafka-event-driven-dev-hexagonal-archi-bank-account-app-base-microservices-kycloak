import { TestBed } from '@angular/core/testing';

import { AccountEventsService } from '../account-events.service';

describe('AccountEventsService', () => {
  let service: AccountEventsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AccountEventsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
