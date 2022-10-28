import {Component, Input, OnInit} from '@angular/core';

import {MenuItemModel} from '../shared/models/menu-item.model';
import {MenuItemInterface} from '../shared/interfaces/menu-item.interface';

@Component({
  selector: 'main-sidemenu-item',
  templateUrl: './main-sidemenu-item.component.html',
  styleUrls: ['./main-sidemenu-item.component.scss']
})
export class MainSidemenuItemComponent implements OnInit {

  private readonly MENU_OPENED_MAX_HEIGHT = 1200;
  private readonly MENU_CLOSED_MAX_HEIGHT = 48;

  @Input() public isSecondaryMenu = false;
  // @ts-ignore
  @Input() public menuItem: MenuItemModel;

  public ngOnInit(): void {
  }

  public checkForChildMenu(): boolean {
    return !!(this.menuItem && this.menuItem.getSub());
  }

  public createMenuItemModel(item: MenuItemInterface): MenuItemModel {
    if (!item) {
      console.warn('createMenuItemModel: Unable to read item: ', item);
      // @ts-ignore
      return undefined;
    } else {
      return new MenuItemModel(item);
    }
  }

  public getMaxHeight(item: MenuItemModel): string {
    if (!item) {
      console.warn('getMaxHeight: Unable to read item: ', item);
      return `${this.MENU_OPENED_MAX_HEIGHT}px`
    } else {
      return (item.isItemOpened())
        ? `${this.MENU_OPENED_MAX_HEIGHT}px`
        : `${this.MENU_CLOSED_MAX_HEIGHT}px`;
    }
  }

}
