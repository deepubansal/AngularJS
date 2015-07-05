'use strict';

/**
 * @ngdoc function
 * @name seeMeApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the seeMeApp
 */
angular.module('seeMeApp')
  .controller('MainCtrl', ['$scope', '$timeout', '$routeParams', 'GpsLogService', 'ConfigService', 'User',
      function ($scope, $timeout, $routeParams, GpsLogService, ConfigService, User) {

    var deviceId = parseInt($routeParams.deviceId);
    $scope.user = new User('dl****4@gmail.com','Car', '#000088', deviceId);
    var retryInterval = 1;
    $scope.opacity=ConfigService.mapConfig.opacity;
    $scope.weight=ConfigService.mapConfig.weight;
    var latestLogPoller = function () {
          GpsLogService.getLatestLog(deviceId)
              .success (function (logs) {
                  $scope.user.createPathOnMap($scope.map);
                  $scope.user.addNewLog(logs[0]);
                  retryInterval=1;
                  $timeout(latestLogPoller, ConfigService.refreshInterval*1000);
              }).error(function(data, status, headers, config) {
                  if (retryInterval <= ConfigService.maxRetryInterval*1000) {
                    retryInterval = retryInterval * 2;
                  }
                  $timeout(latestLogPoller, retryInterval);
              });
        };
    latestLogPoller();
  }]);
