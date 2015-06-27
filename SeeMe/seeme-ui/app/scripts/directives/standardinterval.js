'use strict';

/**
 * @ngdoc directive
 * @name seeMeApp.directive:standardInterval
 * @description
 * # standardInterval
 */
angular.module('seeMeApp')
  .directive('standardInterval', function () {
    return {
      template: '<div></div>',
      restrict: 'E',
      link: function postLink(scope, element, attrs) {
        element.text('this is the standardInterval directive');
      }
    };
  });
