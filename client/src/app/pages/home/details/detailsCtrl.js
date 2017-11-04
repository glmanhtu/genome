(function() {
  'use strict';
  angular
  .module('Genome.pages.home')
  .controller('detailsCtrl', detailsCtrl);
  
  /* @ngInject */
  function detailsCtrl($http, $rootScope, $scope, ProjectService, TaxonomyService, $window, $mdDialog, project, editMode) {    
    $scope.loaded = false;
    $scope.editMode = editMode;
    $scope.project = {};
    if (editMode != true) {
      $scope.project = project;
      ProjectService.getProject(project.projectId, function(response) {
        if (response.status === 200) {
          $scope.projectAllData = response.data;
          $scope.loaded = true;
        }
      });
    }        
    TaxonomyService.findAllTaxonomy(function(response) {
      if (response.status === 200) {
        $scope.taxonomies = response.data;
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