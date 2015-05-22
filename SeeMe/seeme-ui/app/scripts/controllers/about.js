'use strict';

/**
 * @ngdoc function
 * @name seeMeApp.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the seeMeApp
 */
angular.module('seeMeApp')
  .controller('AboutCtrl', function ($scope) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  });
