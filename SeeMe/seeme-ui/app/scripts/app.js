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
      .when('/:deviceId', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl'
      })
      .when('/about', {
        templateUrl: 'views/about.html',
        controller: 'AboutCtrl'
      })
      .when('/test', {
        templateUrl: 'views/test.html',
        controller: 'TestCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });
  });
