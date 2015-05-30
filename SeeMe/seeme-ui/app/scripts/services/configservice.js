'use strict';

/**
 * @ngdoc service
 * @name seeMeApp.ConfigService
 * @description
 * # ConfigService
 * Service in the seeMeApp.
 */
angular.module('seeMeApp')
  .service('ConfigService', function ConfigService() {
      return  {
        baseURL : '../rest/'
      };
  });
