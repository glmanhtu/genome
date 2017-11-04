(function() {
  'use strict';
  angular
  .module('Genome.pages.home')
  .controller('detailsCtrl', detailsCtrl);
  
  /* @ngInject */
  function detailsCtrl($http, $rootScope, $scope, ProjectService, $window, $mdDialog, project, editMode) {    
    $scope.loaded = false;
    $scope.editMode = editMode;
    if (editMode == true) {
      $scope.project = {};
    } else {
      $scope.project = project;
      ProjectService.getProject(project.projectId, function(response) {
        if (response.status === 200) {
          $scope.projectAllData = response.data;
          $scope.loaded = true;
        }
      });
    }        

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