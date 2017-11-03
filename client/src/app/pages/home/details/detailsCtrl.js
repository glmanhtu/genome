(function() {
  'use strict';
  angular
  .module('Genome.pages.home')
  .controller('detailsCtrl', detailsCtrl);
  
  /* @ngInject */
  function detailsCtrl($http, $rootScope, $scope, ProjectsService, $window, $mdDialog) {
    $scope.hide = function() {
      $mdDialog.hide();
    };

    $scope.cancel = function() {
      $mdDialog.cancel();
    };

    $scope.answer = function(answer) {
      $mdDialog.hide(answer);
    };
  }
})();