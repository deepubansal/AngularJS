'use strict';

/**
 * @ngdoc function
 * @name seeMeApp.controller:TestCtrl
 * @description
 * # TestCtrl
 * Controller of the seeMeApp
 */
angular.module('seeMeApp')
  .controller('TestCtrl', ['$scope', '$interval', 'GpsLogService', 'ConfigService', function ($scope, $interval, GpsLogService, ConfigService) {
    $scope.pathCoordinates = [];

    $scope.mapConfig = ConfigService.mapConfig;
    var marker = new google.maps.Marker({
        icon: $scope.mapConfig.stableIcon,
        title: 'My Location'
    });

    $scope.addNewCoordinate = function(newLog) {
        marker.setPosition({lat:newLog.lat, lng:newLog.lon});
        var icon = marker.getIcon();
        icon.rotation = (Math.round(parseFloat(newLog.dir)) + 180)%360;
        marker.setIcon(icon);
        $scope.pathCoordinates.push([newLog.lat, newLog.lon]);
        $scope.latestLog = newLog;
      };

    var fromTime = 1435290258000;
    var toTime = 1435303258000;

    GpsLogService.getLogsForInterval(2, fromTime, toTime).success(function (logs) {
      $scope.ind = 0;
      if (logs.length > 0) {
        $scope.lastLocationTime = logs[logs.length - 1].time;
        var currentCoordinates = [logs[0].lat, logs[0].lon];
        $scope.currentCoordinatesLatLng = new google.maps.LatLng(currentCoordinates[0], currentCoordinates[1]);
        marker.setMap($scope.map);
        $interval(function () {
          var newCoordinate = logs[$scope.ind++];
          $scope.addNewCoordinate(newCoordinate);
        }, 500, logs.length);
      }
    });
  }]);
