'use strict';

/**
 * @ngdoc service
 * @name seeMeApp.User
 * @description
 * # User
 * Factory in the seeMeApp.
 */
angular.module('seeMeApp')
  .factory('User', ['Path', function (Path) {

    function User(username, name, color, deviceId) {
      this.username = username;
      this.name = name;
      this.color = color;
      this.path = null;
      this.deviceId = deviceId;
      this.lastLog = null;
    }

    User.prototype.createPathOnMap = function(map) {
      if (!this.path)
        this.path = new Path(this.name, this.color, map);
    };

    User.prototype.addNewLog  = function(newLog) {
      if (!this.lastLog || this.lastLog.timeAsDate !== newLog.timeAsDate) {
        this.lastLog = newLog;
        this.path.addNewLog(newLog);
      }
    };

    return User;
  }]);
