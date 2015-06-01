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

        icon : {
          url: UtilityService.resolveUrl('images/me.svg'),
          scaledSize: new google.maps.Size(20, 20)
        }

      };
  }]);
