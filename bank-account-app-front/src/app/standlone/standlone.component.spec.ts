import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StandloneComponent } from './standlone.component';

describe('StandloneComponent', () => {
  let component: StandloneComponent;
  let fixture: ComponentFixture<StandloneComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [StandloneComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(StandloneComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
