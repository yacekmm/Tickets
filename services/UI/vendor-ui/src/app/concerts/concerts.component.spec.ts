import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ConcertsComponent} from './concerts.component';

describe('ConcertsComponent', () => {
  let component: ConcertsComponent;
  let fixture: ComponentFixture<ConcertsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConcertsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConcertsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
