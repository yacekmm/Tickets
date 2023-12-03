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
    concertOperations
      .openConcertsList()
      .assertAllConcertsListed(['Rihanna in Rome', 'Rock concert 2'])
  });

});


