'use strict';

/**
 * @ngdoc directive
 * @name seeMeApp.directive:standardInterval
 * @description
 * # standardInterval
 */
angular.module('seeMeApp')
  .directive('standardInterval', ['$timeout', 'UtilityService', function ($timeout, UtilityService) {
    return {
      restrict: 'E',
      link: function postLink(scope, element, attrs) {
        var timeAgo = ''; // initialise the time variable
        var tickInterval = 1000; //ms
        var since;
        scope.$watch(attrs.since, function(value) {
          since = value;
          tick();
        });
        var tick = function() {
            timeAgo = UtilityService.timeSince(new Date(since)); // get the current time
            element.text(timeAgo);
            $timeout(tick, tickInterval); // reset the timer
        };
      }
    };
  }]);
