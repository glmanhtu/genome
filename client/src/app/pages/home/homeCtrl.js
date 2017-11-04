(function() {
  'use strict';
  angular
  .module('Genome.pages.home')
  .controller('homeCtrl', homeCtrl);
  
  /* @ngInject */
  function homeCtrl($http, $rootScope, $scope, ProjectService, $window, $mdDialog, $mdToast) {
    $scope.toggleSearch = false;
    $scope.count = 15;   
    $scope.tablePage = 0;    
    $scope.totalItems = 0;
    $scope.litmitLongString = 120;
    $scope.headers = [
      {
        name:'',
        field:'projectId'
      },
      {
        name: 'Title',
        field: 'title'
      },
      {
        name:'Study Type',
        field: 'studyType'
      },{
        name: 'Taxonomy',
        field: 'taxonomyId'
      }
    ];

    $scope.customClass = {name: 'bold', description:'grey',last_modified: 'grey'};
    $scope.sortable = ['title', 'studyType', 'taxonomyId'];
    $scope.firstColumn = 'projectId';    

    $scope.handleSort = function (field) {
        if ($scope.sortable.indexOf(field) > -1) { return true; } else { return false; }
    };
    $scope.order = function(predicate, reverse) {

        $scope.predicate = {predicate: predicate, reverse: reverse};
        if (predicate == "taxonomyId") {
          predicate = "taxonomy";
        }        
        ProjectService.findAllStudies($rootScope.currentSearchType, $rootScope.keyword, $scope.tablePage, $scope.count, 
                                                predicate, reverse, function(response) {
          if (response.status === 200) {
            $scope.content = response.data.content;
            $scope.totalItems = response.data.totalElements;            
          }
        });
    };
    $scope.order($scope.sortable[0], true);    
    $scope.goToPage = function (page) {
      $window.scrollTo(0, 0);
      $scope.tablePage = page;
      $scope.order($scope.predicate.predicate, $scope.predicate.reverse);
    };

    $rootScope.search = function() {
      $scope.order($scope.predicate.predicate, $scope.predicate.reverse);
    }

    $scope.deleteProject = function(ev, project) {
      var confirm = $mdDialog.confirm()
          .title('Are you sure you want to delete project ' + project.projectId)
          .textContent('Once deleted, we can\'t recover back anymore!')
          .ariaLabel('Delete project')
          .targetEvent(ev)
          .ok('Please do it!')
          .cancel('Maybe another time');

      $mdDialog.show(confirm).then(function() {
        ProjectService.deleteProject(project.projectId, function(response) {
          if (response.status === 200) {
            $mdToast.show(
              $mdToast.simple()
                .textContent('Project ' + project.projectId + ' deleted!')
                .position("top right")
                .hideDelay(3000)
            );
            $scope.content = $scope.content.filter(function(item) { 
                return item.projectId !== project.projectId
            });
            $mdDialog.hide();      
          } else {          
            var message = response.message;          
            $mdToast.show(
              $mdToast.simple()
                .textContent('Error!, ' + message)
                .position("top right")
                .hideDelay(3000)
            );
          }        
        });
      }, function() {        
      });      
    }

    $scope.updateProject = function(ev, project) {
      $mdDialog.show({
        locals: { project: project, mode: 2},
        controller: 'detailsCtrl',
        templateUrl: 'app/pages/home/details/details.tmpl.html',
        parent: angular.element(document.body),
        targetEvent: ev,
        clickOutsideToClose:true,
        fullscreen: false
      });
    }

    $scope.showAdvanced = function(ev, project) {
      $mdDialog.show({
        locals: { project: project, mode: 0},
        controller: 'detailsCtrl',
        templateUrl: 'app/pages/home/details/details.tmpl.html',
        parent: angular.element(document.body),
        targetEvent: ev,
        clickOutsideToClose:true,
        fullscreen: false
      });
    };
  }
})();