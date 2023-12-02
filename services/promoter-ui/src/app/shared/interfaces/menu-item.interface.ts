export interface MenuItemInterface {
  readonly name: string;
  readonly icon: string;
  readonly link: string;
  readonly isOpened: boolean;
  readonly sub?: Array<MenuItemInterface>;
}
