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
      'UtilityService', function ($scope, $interval, $timeout, $routeParams, GpsLogService, ConfigService, UtilityService) {

    var marker = new google.maps.Marker({
        icon: ConfigService.icon,
        title: 'My Location'
    });

    $scope.pathCoordinates = [];
    $scope.addNewCoordinate = function(newLog) {
        marker.setMap($scope.map);
        marker.setPosition({lat:newLog.lat, lng:newLog.lon});
        $scope.pathCoordinates.push([newLog.lat, newLog.lon]);
      };

    $scope.lastLocationTime = 0;
    var deviceId = parseInt($routeParams.deviceId);
    if (deviceId != undefined && typeof deviceId == typeof 1) {
      var latestLogPoller = function () {
            GpsLogService.getLatestLog(deviceId)
                .success (function (logs) {
                    var lastGpsData = logs[0];
                    if (lastGpsData.timeAsDate !==  $scope.lastLocationTime) {
                      $scope.lastLocationTime = lastGpsData.timeAsDate;
                      $scope.addNewCoordinate(lastGpsData);
                    }
                    $timeout(latestLogPoller, 2000);
                }).error(function(data, status, headers, config) {
                    $timeout(latestLogPoller, 10);
                });
          };
      latestLogPoller();
      $scope.timeAgo = ""; // initialise the time variable
      $scope.tickInterval = 1000 //ms
      var tick = function() {
          $scope.timeAgo = UtilityService.timeSince(new Date($scope.lastLocationTime)); // get the current time
          $timeout(tick, $scope.tickInterval); // reset the timer
      }
      $timeout(tick, $scope.tickInterval);
    }
  }]);
