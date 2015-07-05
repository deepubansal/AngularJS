'use strict';

/**
 * @ngdoc directive
 * @name seeMeApp.directive:userPath
 * @description
 * # userPath
 */
angular.module('seeMeApp')
  .directive('userPath', function () {
    return {
      restrict: 'E',
      scope: {
        user: '=userObj',
        weight: '=',
        opacity: '='
      },
      template: '<shape name="polyline" path="{{user.path.pathCoordinates}}" geodesic="true" stroke-color="{{user.color}}" stroke-opacity="{{opacity}}" stroke-weight="{{weight}}"></shape>'

    };
  });
