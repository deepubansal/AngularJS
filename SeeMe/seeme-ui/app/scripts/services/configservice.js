'use strict';

/**
 * @ngdoc service
 * @name seeMeApp.ConfigService
 * @description
 * # ConfigService
 * Service in the seeMeApp.
 */
angular.module('seeMeApp')
  .service('ConfigService', ['UtilityService', function ConfigService(UtilityService) {
      return  {

        baseURL : '../rest/',
        // After every continuous failure, interval between subsequent retries will exponentially increase with power of 2.
        // Maximum Interval (in seconds) after which interval for retry will not increase for further retries.
        maxRetryInterval: 120,

        // Map refresh Interval with new GPS Data
        refreshInterval: 2,
        mapConfig: {
          movingIcon : {
            path: google.maps.SymbolPath.BACKWARD_CLOSED_ARROW,
            scale: 5,
            strokeWeight: 2.5,
            anchor: new google.maps.Point(0, -4),
            fillColor: '#008800',
            fillOpacity: 1,
            strokeColor:'#000000'
          },
          stableIcon : {
            path:google.maps.SymbolPath.CIRCLE,
            scale: 5,
            strokeWeight: 2.5,
            fillColor: '#008800',
            fillOpacity: 1,
            strokeColor:'#000000'
          },
          stroke: 5,
          color: '#008800',
          opacity: 1.0
        }
      };
  }]);
