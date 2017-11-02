(function () {
  'use strict';

  angular.module('Genome.theme.components')
      .directive('searchbar', searchbar);

  /** @ngInject */
  function searchbar() {
    return {
      restrict: 'E',
      templateUrl: 'app/theme/components/searchbar/searchbar.html',
      controller: 'searchbarCtrl'
    };
  }

})();