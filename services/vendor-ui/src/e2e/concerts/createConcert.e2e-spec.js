
describe('ui', function() {

  it('creates concert', function() {
    browser.ignoreSynchronization = true;
    browser.get('http://localhost:4200/');
    browser.driver.getTitle().then(function(pageTitle) {
      expect(pageTitle).toEqual('Vendor UI');
    });
  });

});
