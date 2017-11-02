/**
 * Change top "Daily Downloads", "Active Users" values with animation effect
 */
(function () {
  'use strict';

  angular.module('Genome.theme')
      .directive('showFocus', showFocus);

  /** @ngInject */
  function showFocus($timeout) {
    return function(scope, element, attrs) {
        scope.$watch(attrs.showFocus,
          function (newValue) {
            $timeout(function() {
                newValue && element.focus();
            });
          },true);
      };
  }

})();