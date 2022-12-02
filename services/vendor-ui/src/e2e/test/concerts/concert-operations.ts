import {BrowserTools} from "../shared/browser-tools";
import {By, element} from "protractor";

export class ConcertOperations {
  private browserTools: BrowserTools;

  constructor(browserTools: BrowserTools) {
    this.browserTools = browserTools;
  }

  assertPageIsOpened() {
    this.browserTools.getPageTitle().then(pageTitle => expect(pageTitle).toEqual('Vendor UI'));
    return this;
  }

  openAddConcert() {
    element(By.id("menu-concerts-add")).click()
    return this;
  }

  createConcert() {
    element(By.id("input-concert-title")).sendKeys("concert Title");
    element(By.id("create-concert-submit")).click();
    return this;
  }

  assertCreateConcertSuccess() {
    let successConfirmation = element(By.className("mat-simple-snackbar"));
    this.browserTools.waitUntilIsVisible(successConfirmation)
      .then(unused =>
        expect(successConfirmation.isDisplayed()).toBeTruthy()
      );
    return this;
  }

  openConcertsList() {
    element(By.id("menu-concerts-list")).click();
    return this;
  }

  assertAllConcertsListed(expectedConcertTitles: string[]) {
    element.all(By.className('mat-row'))
      .all(By.className('concert-title'))
      .getText()
      .then(value =>
        expect(value).toEqual(expectedConcertTitles)
      );
  }
}
