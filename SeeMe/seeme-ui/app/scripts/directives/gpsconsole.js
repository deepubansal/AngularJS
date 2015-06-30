'use strict';

/**
 * @ngdoc directive
 * @name seeMeApp.directive:gpsConsole
 * @description
 * # gpsConsole
 */
angular.module('seeMeApp')
  .directive('gpsConsole', function () {
    return {
      template: '<div>Speed: {{speed}} Km/hr</div>',
      restrict: 'E',
      link: function postLink(scope, element, attrs) {
        scope.$watch(attrs.gpsInfo, function(value) {
          if (value && value.spd) {
            scope.speed = Math.round(parseFloat(value.spd)*18.0/5.0);
          }
        });
      }
    };
  });
