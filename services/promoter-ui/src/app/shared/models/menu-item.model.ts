import {MenuItemInterface} from '../interfaces/menu-item.interface';

export class MenuItemModel implements MenuItemInterface {

  public name: string = "";
  public icon: string = "";
  public link: string = "";
  public id: string = "";
  public isOpened: boolean = true;
  public sub: Array<MenuItemInterface> = [];

  constructor(definition: MenuItemInterface) {
    Object.keys(definition).forEach((name) => {
      // @ts-ignore
      this[name] = definition[name];
    });
  }

  public getName(): string {
    return this.name;
  }

  public getIcon(): string {
    return this.icon;
  }

  public getLink(): string {
    return this.link;
  }

  public getId(): string {
    return this.id;
  }

  public isItemOpened(): boolean {
    return this.isOpened;
  }

  public toggleOpen(): void {
    this.isOpened = !this.isOpened;
  }

  public getSub(): Array<MenuItemInterface> {
    if (!this.sub) {
      return [];
    }
    return this.sub;
  }
}
