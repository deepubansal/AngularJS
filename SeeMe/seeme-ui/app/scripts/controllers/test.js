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
     $scope.addNewCoordinate = function(newLog) {
        $scope.pathCoordinates.push([newLog.lat, newLog.lon]);
      };

    var toTime = new Date().getTime();
    // var toTime = 1432771882000;

    GpsLogService.getLogsTill(1, toTime).success(function (logs) {
      $scope.ind = 0;
      if (logs.length > 0) {
        $scope.lastLocationTime = logs[logs.length - 1].time;
        var currentCoordinates = [logs[0].lat, logs[0].lon];
        $scope.currentCoordinatesLatLng = new google.maps.LatLng(currentCoordinates[0], currentCoordinates[1]);
        var marker = new google.maps.Marker({
            position: $scope.currentCoordinatesLatLng,
            map: $scope.map,
            icon: ConfigService.icon,
            title: 'My Location'
        });
        marker.setMap($scope.map);
        $interval(function () {
          var newCoordinate = logs[$scope.ind++];
          $scope.addNewCoordinate(newCoordinate);
        }, 2000, logs.length);
      }
    });
  }]);
