'use strict';

/**
 * @ngdoc function
 * @name seeMeApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the seeMeApp
 */
angular.module('seeMeApp')
  .controller('MainCtrl', ['$scope', 'GpsLogService', function ($scope, GpsLogService) {
    GpsLogService.getLatestLog(1).then (function (data) {
      console.log(data);
    });
  }]);
