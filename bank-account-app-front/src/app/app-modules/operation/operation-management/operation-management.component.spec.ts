import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OperationManagementComponent } from './operation-management.component';

describe('OperationManagementComponent', () => {
  let component: OperationManagementComponent;
  let fixture: ComponentFixture<OperationManagementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OperationManagementComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(OperationManagementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
