'use strict';

describe('Service: path', function () {

  // load the service's module
  beforeEach(module('seeMeApp'));

  // instantiate service
  var path;
  beforeEach(inject(function (_path_) {
    path = _path_;
  }));

  it('should do something', function () {
    expect(!!path).toBe(true);
  });

});
