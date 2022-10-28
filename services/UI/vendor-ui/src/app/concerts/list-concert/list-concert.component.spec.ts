import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ListConcertComponent} from './list-concert.component';

describe('ListConcertComponent', () => {
  let component: ListConcertComponent;
  let fixture: ComponentFixture<ListConcertComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListConcertComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListConcertComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
