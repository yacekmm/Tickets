import {by, element} from 'protractor';

export class ConcertOperations {

  createApp(name: string, email: string, applicationType: string) {
    element(by.id('input-app-name')).sendKeys(name);
    element(by.id('input-owner')).sendKeys(email);

    if (applicationType === "CONFIDENTIAL") {
      element(by.css('#application-type-confidential .mat-radio-container')).click();
    } else {
      element(by.css('#application-type-public .mat-radio-container')).click();
    }

    return element(by.id('create-app-submit')).click();
  }

  displaysSuccessMessage() {
    return element(by.id('create-app-success')).isDisplayed();
  }

  displaysAppId() {
    return element(by.id('created-app-id')).getText()
      .then((text: string) => {
        return expect(text.length).toEqual(36);
      });
  }

  displaysAppSecret() {
    return element(by.id('created-app-secret')).getText()
      .then((text: string) => {
        return expect(text.length).toEqual(32);
      });
  }

  isAppSecretDisplayed() {
    return element(by.id('created-app-secret')).isPresent();
  }

  displaysCreateNextAppButton() {
    return element(by.id('create-next')).isDisplayed();
  }

  showAppsListButtonIsDisplayed() {
    return this.getShowAppsButton().isDisplayed();
  }

  showAppsListButtonClick() {
    return this.getShowAppsButton().click();
  }

  private getShowAppsButton() {
    return element(by.id('show-apps'));
  }
}
