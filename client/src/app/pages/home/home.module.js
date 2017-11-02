(function () {
  'use strict';

  angular.module('Genome.pages.home', [])
    .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider) {
      $stateProvider
          .state('home', {
            url: '/home',
            templateUrl: 'app/pages/home/home.html',
            controller: 'homeCtrl'
          });
    }

})();
