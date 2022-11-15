import {browser, By, element} from 'protractor';
import {BrowserTools} from "../shared/browser-tools";

export class ConcertOperations {
  private browserTools: BrowserTools;

  constructor(browserTools: BrowserTools) {
    this.browserTools = browserTools;
  }


  assertPageIsOpened() {
    browser.driver.getTitle().then(pageTitle => expect(pageTitle).toEqual('Vendor UI'));
    return this;
  }

  createConcert() {
    element(By.id("input-concert-title")).sendKeys("concert Title");
    element(By.id("create-concert-submit")).click();
    return this;
  }

  openAddConcert() {
    element(By.id("menu-concerts-add")).click()
    return this;
  }

  assertCreateConcertSuccess() {
    let successConfirmation = element(By.className("mat-simple-snackbar"));
    this.browserTools.waitForElementToDisplay(successConfirmation)
      .then(unused =>
        expect(successConfirmation.isDisplayed()).toBeTruthy()
      );
    return this;
  }

  openListConcerts() {
    element(By.id("menu-concerts-list")).click();
    return this;
  }

  assertConcertsListed(expectedTitles: string[]) {
    element.all(By.className('mat-row'))
      .all(By.className('concert-title'))
      .getText()
      .then(value =>
        expect(value).toEqual(expectedTitles)
      );
    return this;
  }
}
