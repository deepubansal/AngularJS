'use strict';

/**
 * @ngdoc service
 * @name seeMeApp.GpsLogService
 * @description
 * # GpsLogService
 * Service in the seeMeApp.
 */
angular.module('seeMeApp')
  .service('GpsLogService', ['$http', 'ConfigService', function ($http, ConfigService) {
    var base = ConfigService.baseURL;
    return {
      getLatestLog : function (deviceId) {
           return $http.get(base + 'gpslog/get', { params: { deviceId : deviceId } });
         },
      getLogsSince : function (deviceId, fromTime) {
          return $http.get(base + 'gpslog/get',{ params: { deviceId : deviceId, fromTime : fromTime } });
        },
      getLogsTill : function (deviceId, toTime) {
          return $http.get(base + 'gpslog/get',{ params: { deviceId : deviceId, toTime : toTime } });
        },
      getLogsForInterval : function (deviceId, fromTime, toTime) {
          return $http.get(base + 'gpslog/get', { params: { deviceId: deviceId, fromTime : fromTime, toTime : toTime } });
        }
      };
    }]
    );
