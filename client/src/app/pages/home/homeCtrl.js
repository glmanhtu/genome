(function() {
  'use strict';
  angular
  .module('Genome.pages.home')
  .controller('homeCtrl', homeCtrl);
  
  /* @ngInject */
  function homeCtrl($http, $timeout, $scope, PagerService, ProjectsService) {
    $scope.toggleSearch = false;
    $scope.count = 15;   
    $scope.tablePage = 0;    
    $scope.totalItems = 0;
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
          predicate = "taxonomies";
        }
        ProjectsService.findAllStudies($scope.tablePage, $scope.count, predicate, reverse, function(response) {
          if (response.status === 200) {
            $scope.content = response.data.content;
            $scope.totalItems = response.data.totalElements;            
          }
        });
    };
    $scope.order($scope.sortable[0], true);    
    $scope.goToPage = function (page) {
      $scope.tablePage = page;
      $scope.order($scope.predicate.predicate, $scope.predicate.reverse);
    };
  }
})();