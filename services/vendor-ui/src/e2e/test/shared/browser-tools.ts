import {browser, ElementFinder, protractor} from 'protractor';

export class BrowserTools {

  setup() {
    browser.ignoreSynchronization = true;
    browser.get('http://localhost:4200/');
  }

  waitForElementToDisplay(element: ElementFinder) {
    let expected = protractor.ExpectedConditions;
    return browser.wait(expected.visibilityOf(element));
  }
}
