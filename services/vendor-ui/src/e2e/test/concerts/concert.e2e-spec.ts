import {browser, By, element} from "protractor";
import {ConcertOperations} from "./concert-operations";
import {BrowserTools} from "../shared/browser-tools";

describe('ui', () => {

  let browserTools: BrowserTools;
  let concertOperations: ConcertOperations;

  beforeEach(() => {
    browserTools = new BrowserTools()
    concertOperations = new ConcertOperations()
    browserTools.setup();
  });

  it('opens page', () => {
    browser.driver.getTitle().then(pageTitle => expect(pageTitle).toEqual('Vendor UI'));
  });

  it('creates concert', function () {
    element(By.id("menu-concerts-add")).click()
    element(By.id("input-concert-title")).sendKeys("concert Title");
    element(By.id("create-concert-submit")).click();
    let successConfirmation = element(By.className("mat-simple-snackbar"));
    browserTools.waitForElementToDisplay(successConfirmation)
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


