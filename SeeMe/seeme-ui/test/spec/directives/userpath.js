'use strict';

describe('Directive: userPath', function () {

  // load the directive's module
  beforeEach(module('seeMeApp'));

  var element,
    scope;

  beforeEach(inject(function ($rootScope) {
    scope = $rootScope.$new();
  }));

  it('should make hidden element visible', inject(function ($compile) {
    element = angular.element('<user-path></user-path>');
    element = $compile(element)(scope);
    expect(element.text()).toBe('this is the userPath directive');
  }));
});
