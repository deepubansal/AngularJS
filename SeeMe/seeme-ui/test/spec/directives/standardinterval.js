'use strict';

describe('Directive: standardInterval', function () {

  // load the directive's module
  beforeEach(module('seeMeApp'));

  var element,
    scope;

  beforeEach(inject(function ($rootScope) {
    scope = $rootScope.$new();
  }));

  it('should make hidden element visible', inject(function ($compile) {
    element = angular.element('<standard-interval></standard-interval>');
    element = $compile(element)(scope);
    expect(element.text()).toBe('this is the standardInterval directive');
  }));
});
