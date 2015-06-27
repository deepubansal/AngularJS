'use strict';

/**
 * @ngdoc overview
 * @name seeMeApp
 * @description
 * # seeMeApp
 *
 * Main module of the application.
 */
angular
  .module('seeMeApp', [
    'ngResource',
    'ngRoute',
    'ngMap'
  ])
  .config(function($routeProvider) {
    $routeProvider
      .when('/about', {
        templateUrl: 'views/about.html',
        controller: 'AboutCtrl'
      })
      .when('/test', {
        templateUrl: 'views/test.html',
        controller: 'TestCtrl'
      })
      .when('/:deviceId', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl',
        resolve:   {
          id : function($q, $route) {
                var deferred = $q.defer();
                var id = parseInt($route.current.params.deviceId);
                if (!isNaN(id)) {
                  deferred.resolve();
                } else {
                  deferred.reject('devcieId is not a number');
                }
                return deferred.promise;
          }
        }
      })
      .otherwise({
        redirectTo: '/'
      });
  });
