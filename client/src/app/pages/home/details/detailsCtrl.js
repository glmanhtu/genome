(function() {
  'use strict';
  angular
  .module('Genome.pages.home')
  .controller('detailsCtrl', detailsCtrl);
  
  /* @ngInject */
  function detailsCtrl($http, $rootScope, $scope, ProjectService, TaxonomyService, $window, $mdDialog, $mdToast, project, mode) {    
    $scope.loaded = true;
    $scope.mode = mode;
    $scope.project = {};
    if (mode === 0 || mode === 2) {
      $scope.title = project.title;
    } else if (mode === 1) {
      $scope.title = "Create new project";
    }
    if (mode != 1) {
      $scope.loaded = false;
      $scope.project = project;
      ProjectService.getProject(project.projectId, function(response) {
        if (response.status === 200) {
          $scope.project.description = response.data.description;
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

    $scope.createNewProject = function() {
      $scope.loaded = false;
      ProjectService.createProject($scope.project, function(response) {
        if (response.status === 200) {
          $mdToast.show(
            $mdToast.simple()
              .textContent('Project ' + $scope.project.projectId + ' created!')
              .position("top right")
              .hideDelay(3000)
          );    
          $mdDialog.hide();      
        } else {
          $scope.loaded = true;
          var message = response.message;
          if (response.message == "validation error") {
            message = response.data.fieldErrors[0].objectName + " " + response.data.fieldErrors[0].field;
          }
          $mdToast.show(
            $mdToast.simple()
              .textContent('Error!, ' + message)
              .position("top right")
              .hideDelay(3000)
          );
        }        
      })
    }
  }
})();