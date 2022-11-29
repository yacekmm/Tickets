import {browser, ElementFinder, protractor} from "protractor";

export class BrowserTools {

  initBrowser() {
    browser.ignoreSynchronization = true;
    browser.get('http://localhost:4200/');
  }

  getPageTitle() {
    return browser.driver.getTitle();
  }

  waitUntilIsVisible(element: ElementFinder) {
    let expected = protractor.ExpectedConditions;
    return browser.wait(expected.visibilityOf(element));
  }
}
