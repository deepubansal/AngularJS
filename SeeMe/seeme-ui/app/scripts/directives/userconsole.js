'use strict';

/**
 * @ngdoc directive
 * @name seeMeApp.directive:userConsole
 * @description
 * # userConsole
 */
angular.module('seeMeApp')
  .directive('userConsole', function () {
    return {
      templateUrl: '../views/userconsole.html',
      restrict: 'E',
      scope: {
          user: '=userObj'
      }
    };
  });
