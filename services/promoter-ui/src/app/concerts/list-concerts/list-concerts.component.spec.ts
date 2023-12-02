import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ListConcertsComponent} from './list-concerts.component';

describe('ListConcertComponent', () => {
  let component: ListConcertsComponent;
  let fixture: ComponentFixture<ListConcertsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListConcertsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListConcertsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
