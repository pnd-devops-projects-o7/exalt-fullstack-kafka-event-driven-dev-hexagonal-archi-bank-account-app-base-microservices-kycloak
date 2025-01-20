import { TestBed } from '@angular/core/testing';

import { SuperusersService } from './superusers.service';

describe('SuperusersService', () => {
  let service: SuperusersService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SuperusersService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
