import {browser, By, element, protractor} from "protractor";
import {BrowserTools} from "../shared/browser-tools";

describe('ui', () => {

  let browserTools: BrowserTools

  beforeEach(() => {
    browserTools = new BrowserTools();
    browserTools.initBrowser();
  });

  it('opens page', () => {
    browserTools.getPageTitle().then(pageTitle => expect(pageTitle).toEqual('Promoter Platform'));
  });

  it('creates concert', function () {
    element(By.id("menu-concerts-add")).click()
    element(By.id("input-concert-title")).sendKeys("concert Title");
    element(By.id("create-concert-submit")).click();
    let successConfirmation = element(By.className("mat-simple-snackbar"));
    browserTools.waitUntilIsVisible(successConfirmation)
      .then(unused =>
        expect(successConfirmation.isDisplayed()).toBeTruthy()
      );
  });

  it('lists stub concerts', () => {
    element(By.id("menu-concerts-list")).click();

    element.all(By.className('mat-row'))
      .all(By.className('concert-title'))
      .getText()
      .then(value =>
        expect(value).toEqual(['Rihanna in Rome', 'Rock concert 2'])
      );
  });


});


