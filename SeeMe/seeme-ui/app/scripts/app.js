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
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl'
      })
      .when('/about', {
        templateUrl: 'views/about.html',
        controller: 'AboutCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });
  });
