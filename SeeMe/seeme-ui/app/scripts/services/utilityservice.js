'use strict';

/**
 * @ngdoc service
 * @name seeMeApp.UtilityService
 * @description
 * # UtilityService
 * Service in the seeMeApp.
 */
angular.module('seeMeApp')
  .service('UtilityService', function UtilityService() {

    return {

        timeSince : function (date) {
            var seconds = Math.floor((new Date() - date) / 1000);
            var interval = Math.floor(seconds / 31536000);
            if (interval > 1) {
                return interval + ' years';
            }
            interval = Math.floor(seconds / 2592000);
            if (interval > 1) {
                return interval + ' months';
            }
            interval = Math.floor(seconds / 86400);
            if (interval > 1) {
                return interval + ' days';
            }
            interval = Math.floor(seconds / 3600);
            if (interval > 1) {
                return interval + ' hours';
            }
            interval = Math.floor(seconds / 60);
            if (interval > 1) {
                return interval + ' minutes';
            }
            return Math.floor(seconds) + ' seconds';
          },

        resolveUrl : function (url){
            var a = document.createElement('a');
            a.href=url; // set string url
            url = a.href; // get qualified url
            return url;
          }
        };
  });
