'use strict';

/**
 * @ngdoc function
 * @name seeMeApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the seeMeApp
 */
angular.module('seeMeApp')
  .controller('MainCtrl', ['$scope', '$interval', '$timeout', '$routeParams', 'GpsLogService', 'ConfigService',
      function ($scope, $interval, $timeout, $routeParams, GpsLogService, ConfigService) {

    $scope.mapConfig = ConfigService.mapConfig;
    var marker = new google.maps.Marker({
        icon: $scope.mapConfig.icon,
        title: 'My Location'
    });

    $scope.pathCoordinates = [];
    $scope.addNewCoordinate = function(newLog) {
        $scope.pathCoordinates.push([newLog.lat, newLog.lon]);
        marker.setPosition({lat:newLog.lat, lng:newLog.lon});
        if ($scope.pathCoordinates.length == 1)
          marker.setMap($scope.map);
      };

    $scope.lastGpsData = {};
    var retryInterval = 1;
    $scope.locationChangedTime = 0;
    var deviceId = parseInt($routeParams.deviceId);
    if (deviceId != undefined && typeof deviceId == typeof 1) {
      var latestLogPoller = function () {
            GpsLogService.getLatestLog(deviceId)
                .success (function (logs) {
                    $scope.lastGpsData = logs[0];
                    if ($scope.lastGpsData.timeAsDate !==  $scope.locationChangedTime) {
                      $scope.locationChangedTime = $scope.lastGpsData.timeAsDate;
                      $scope.addNewCoordinate($scope.lastGpsData);
                    }
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
    }
  }]);
