import {ConcertOperations} from "./concert-operations";
import {BrowserTools} from "../shared/browser-tools";

describe('concert', () => {

  let browserTools: BrowserTools;
  let concertOperations: ConcertOperations;

  beforeEach(() => {
    browserTools = new BrowserTools()
    concertOperations = new ConcertOperations(browserTools)
    browserTools.setup();
  });

  it('opens page', () => {
    concertOperations
      .assertPageIsOpened();
  });

  it('creates concert', function () {
    concertOperations
      .openAddConcert()
      .createConcert()
      .assertCreateConcertSuccess();
  });

  it('lists stub concerts', () => {
    concertOperations
      .openListConcerts()
      .assertConcertsListed(['Rihanna in Rome', 'Rock concert 2']);
  });


});


