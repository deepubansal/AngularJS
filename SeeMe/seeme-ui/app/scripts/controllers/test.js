'use strict';

/**
 * @ngdoc function
 * @name seeMeApp.controller:TestCtrl
 * @description
 * # TestCtrl
 * Controller of the seeMeApp
 */
angular.module('seeMeApp')
  .controller('TestCtrl', ['$scope', '$interval', 'GpsLogService', 'User', 'ConfigService', function ($scope, $interval, GpsLogService, User, ConfigService) {

    // var fromTime = 1435296165000;
    // var toTime =   1435303258000;
    var fromTime = 1434878141000;
    var toTime = 1434879041000;
    var user1 = new User('deepu.bansal@gmail.com','Deepak', '#008800', 1);
    var user2 = new User('dl****4@gmail.com','Car', '#000088', 2);
    $scope.users = [user1, user2];
    var createProcessLogsFunction = function(user) {
      return function (logs) {
        if (logs.length > 0) {
          var index = 0;
          user.createPathOnMap($scope.map);
          $interval(function () {
            var newLog = logs[index++];
            user.addNewLog(newLog);
          }, 500, logs.length);
        }
      };
    };

    $scope.opacity=ConfigService.mapConfig.opacity;
    $scope.weight=ConfigService.mapConfig.weight;
    for (var i = $scope.users.length - 1; i >= 0; i--) {
      GpsLogService.getLogsForInterval($scope.users[i].deviceId, fromTime, toTime)
        .success(createProcessLogsFunction($scope.users[i]));
    }
  }]);
