'use strict';

/**
 * @ngdoc service
 * @name seeMeApp.path
 * @description
 * # path
 * Factory in the seeMeApp.
 */
angular.module('seeMeApp')
  .factory('Path', ['ConfigService', '$timeout', function (ConfigService, $timeout) {
      function Path(name, color, map) {
        this.stableIcon = angular.copy(ConfigService.mapConfig.stableIcon);
        this.stableIcon.fillColor = color;
        this.movingIcon = angular.copy(ConfigService.mapConfig.movingIcon);
        this.movingIcon.fillColor = color;
        this.pathCoordinates = null;
        this.marker = new google.maps.Marker({
            icon: this.stableIcon,
            title: name
        });
        this.map = map;
        this.marker.setMap(this.map);
        this.lastUpdated = new Date();
      }

      var movingTimer = ConfigService.movingTimer;
      var resetMovingTimer = null;
      var setMoving = function(dir, user) {
        user.movingIcon.rotation = (dir + 180)%360;
        if (user.marker.getIcon() !== user.movingIcon) {
            user.marker.setIcon(user.movingIcon);
        }
        if (resetMovingTimer) {
          $timeout.cancel(resetMovingTimer);
        }
        resetMovingTimer = $timeout(function() { setStable(user) }, movingTimer);
      }
      var setStable = function(user) {
        if (user.marker.getIcon() !== user.stableIcon) {
            user.marker.setIcon(user.stableIcon);
        }
        if (resetMovingTimer) {
          $timeout.cancel(resetMovingTimer);
        }
      }
      Path.prototype.addNewLog = function (newLog) {
        var point = new google.maps.LatLng(parseFloat(newLog.lat),parseFloat(newLog.lon));
        this.marker.setPosition(point);
        var currentBounds = this.map.getBounds() // get bounds of the map object's viewport
        if(!currentBounds || !currentBounds.contains(point)){
          this.map.setCenter(point);
        }
        if (!this.pathCoordinates) {
          this.pathCoordinates = [];
        }
        this.pathCoordinates.push([newLog.lat, newLog.lon]);
        var dir = Math.round(parseFloat(newLog.dir));
        var speed = Math.round(parseFloat(newLog.spd)*18.0/5.0)
        if (speed >= 1) {
          setMoving(dir, this);
        }
        else {
           setStable(this);
        }
      };
      return Path;
  }]);
