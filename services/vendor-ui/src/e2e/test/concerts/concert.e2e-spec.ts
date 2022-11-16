import {By, element} from "protractor";
import {BrowserTools} from "../shared/browser-tools";
import {ConcertOperations} from "./concert-operations";

describe('ui', () => {

  let browserTools: BrowserTools
  let concertOperations: ConcertOperations

  beforeEach(() => {
    browserTools = new BrowserTools();
    browserTools.initBrowser();
    concertOperations = new ConcertOperations(browserTools);
  });

  it('opens page', () => {
    concertOperations
      .assertPageIsOpened();
  });

  it('creates concert', function () {
    concertOperations
      .openAddConcert()
      .createConcert()
      .assertCreateConcertSuccess()
  });

  it('lists stub concerts', () => {
    //open concerts list
    element(By.id("menu-concerts-list")).click();

    //assert expected concerts are listed
    element.all(By.className('mat-row'))
      .all(By.className('concert-title'))
      .getText()
      .then(value =>
        expect(value).toEqual(['Rihanna in Rome', 'Rock concert 2'])
      );
  });


});


