import {ComponentFixture, TestBed, waitForAsync} from '@angular/core/testing';
import {RouterTestingModule} from '@angular/router/testing';

import {MatIcon} from '@angular/material/icon';
import {MatListItem, MatNavList} from '@angular/material/list';
import {MatRipple} from '@angular/material/core';


import {MainSidemenuItemComponent} from './main-sidemenu-item.component';

describe('MainSidemenuItemComponent', () => {
  let component: MainSidemenuItemComponent;
  let fixture: ComponentFixture<MainSidemenuItemComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule
      ],
      declarations: [
        MainSidemenuItemComponent,
        MatIcon,
        MatListItem,
        MatNavList,
        MatRipple
      ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MainSidemenuItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
