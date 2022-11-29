import {Component} from '@angular/core';
import {MenuItemModel} from './shared/models/menu-item.model';
import {Router} from '@angular/router';
import {MenuItemInterface} from './shared/interfaces/menu-item.interface';
import {declaredMenuItems} from "./app.mainmenu-items";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'vendor-ui';

  public menuItems: Array<MenuItemModel> = [];

  constructor(private router: Router) {
  }

  public ngOnInit(): void {
    this.setupMenuElements();
  }

  private setupMenuElements(): void {
    const items: Array<MenuItemInterface> = declaredMenuItems;
    if (!items) {
      console.warn('Unable to read items: ', items);
      return;
    }

    items.forEach((item: MenuItemInterface) => {
        this.menuItems.push(new MenuItemModel(item));
    });
  }
}
