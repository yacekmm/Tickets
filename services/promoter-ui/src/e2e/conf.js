const {SpecReporter} = require('jasmine-spec-reporter');

var Jasmine2HtmlReporter = require('protractor-jasmine2-html-reporter');
var jasmine2HtmlReporter = new Jasmine2HtmlReporter({
  savePath: 'reports',
  takeScreenshots: true,
  takeScreenshotsOnlyOnFailures: false,
  fileName: 'protractor-report.html'
});

exports.config = {
  framework: 'jasmine',
  capabilities: {
    browserName: 'chrome',
  },
  directConnect: true,
  // baseUrl: 'http://localhost:4200/',
  specs: [
    './**/*.e2e-spec*'
  ],

  onPrepare() {
    require('ts-node').register({
      project: require('path').join(__dirname, './tsconfig.e2e.json')
    });
    jasmine.getEnv().addReporter(new SpecReporter({spec: {displayStacktrace: true}}));
    jasmine.getEnv().addReporter(jasmine2HtmlReporter);
  }
};
