(function () {
  'use strict';

  angular.module('Genome.pages', [
      'ui.router',
      'Genome.pages.home'
  ]).config(routeConfig);

    /** @ngInject */
    function routeConfig($urlRouterProvider) {
        $urlRouterProvider
        .otherwise('/home');
    }

})();
