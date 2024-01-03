import {browser, By, element, protractor} from "protractor";

describe('ui', () => {

  beforeEach(() => {
    browser.ignoreSynchronization = true;
    browser.get('http://localhost:4200/');
  });

  it('opens page', () => {
    browser.driver.getTitle().then(pageTitle => expect(pageTitle).toEqual('Promoter Platform'));
  });

  it('creates concert', function () {
    element(By.id("menu-concerts-add")).click()
    element(By.id("input-concert-title")).sendKeys("concert Title");
    element(By.id("create-concert-submit")).click();
    let successConfirmation = element(By.className("mat-simple-snackbar"));
    let expected = protractor.ExpectedConditions;
    browser.wait(expected.visibilityOf(successConfirmation))
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


