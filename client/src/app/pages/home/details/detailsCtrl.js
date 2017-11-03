(function() {
  'use strict';
  angular
  .module('Genome.pages.home')
  .controller('detailsCtrl', detailsCtrl);
  
  /* @ngInject */
  function detailsCtrl($http, $rootScope, $scope, ProjectsService, $window, $mdDialog, project) {

    $scope.project = project;
    $scope.loaded = false;
    ProjectsService.getProject(project.projectId, function(response) {
      if (response.status === 200) {
        $scope.projectAllData = response.data;
        $scope.loaded = true;
      }
    });

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