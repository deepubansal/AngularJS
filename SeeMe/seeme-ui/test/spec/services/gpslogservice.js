'use strict';

describe('Service: GpsLogService', function () {

  // load the service's module
  beforeEach(module('seeMeApp'));

  // instantiate service
  var GpsLogService;
  beforeEach(inject(function (_GpsLogService_) {
    GpsLogService = _GpsLogService_;
  }));

  it('should do something', function () {
    expect(!!GpsLogService).toBe(true);
  });

});
