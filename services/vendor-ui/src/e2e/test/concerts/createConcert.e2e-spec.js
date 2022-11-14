
describe('ui', function() {

  beforeEach(() => {
    browser.ignoreSynchronization = true;
    browser.get('http://localhost:4200/');
  });

  it('opens page', function() {
    browser.driver.getTitle().then(pageTitle => expect(pageTitle).toEqual('Vendor UI'));
  });

  it('creates concert', function() {

  });

  it('lists stub concerts', function() {

  });

});
