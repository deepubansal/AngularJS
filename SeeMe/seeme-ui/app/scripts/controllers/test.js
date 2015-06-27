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

    var marker = new google.maps.Marker({
        icon: ConfigService.icon,
        title: 'My Location'
    });

    $scope.addNewCoordinate = function(newLog) {
        marker.setPosition({lat:newLog.lat, lng:newLog.lon});
        $scope.pathCoordinates.push([newLog.lat, newLog.lon]);
      };

    var fromTime = 1435300258000;
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
