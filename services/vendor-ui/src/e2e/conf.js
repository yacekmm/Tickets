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
};
