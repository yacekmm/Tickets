import {ComponentFixture, TestBed} from '@angular/core/testing';

import {CreateConcertComponent} from './create-concert.component';

describe('CreateConcertComponent', () => {
  let component: CreateConcertComponent;
  let fixture: ComponentFixture<CreateConcertComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateConcertComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreateConcertComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
