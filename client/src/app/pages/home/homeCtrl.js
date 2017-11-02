(function() {
    'use strict';
    angular
    .module('Genome.pages.home')
    .controller('homeCtrl', homeCtrl);
    homeCtrl.$inject = ['$http', '$timeout', '$scope', 'DOMAIN_URL'];
    /* @ngInject */
    function homeCtrl($http, $timeout, $scope, goodService, searchService, PagerService, DOMAIN_URL) {
        $scope.toggleSearch = false;
          $scope.headers = [
            {
              name:'',
              field:'project_id'
            }, {
                name: 'Title',
                field: 'title'
            },{
              name:'Study Type',
              field: 'study_type'
            },{
                name: 'Taxonomy',
                field: 'taxonomy_id'
            }
          ];

          $scope.content = [
            {
              project_id:'PRJEB10964',
              study_type: 'Control Set',
              title: 'UK10K Avon Longitudinal Study of Parents and Children (ALSPAC) Variants',
              taxonomy_id: 9606
            },{
              project_id:'PRJEB10964',
              study_type: 'Control Set',
              title: 'Vervet Genetic Mapping Project',
                taxonomy_id: 9606
            },{
              project_id:'PRJEB10964',
              study_type: 'Control Set',
              title: 'Genetic evidence for an origin of the Armenians from Bronze Age mixing of multiple populations',
                taxonomy_id: 9606
            },{
              project_id:'PRJEB10964',
              study_type: 'Aggregate',
              title: 'Prognostic factors of stable remission after cessation of TKI therapy in chronic myeloid leukemia by whole exome sequencing',
                taxonomy_id: 9606
            },{
              project_id:'PRJEB10964',
              study_type: 'Control Set',
              title: 'Study of Major Depression in Chinese women',
                taxonomy_id: 9606
            }
          ];

          $scope.customClass = {name: 'bold', description:'grey',last_modified: 'grey'};
          $scope.sortable = ['study_type', 'title', 'taxonomy_id'];
          $scope.project_id = 'project_id';
          $scope.count = 10;

          $scope.tablePage = 0;
            $scope.nbOfPages = function () {
              return Math.ceil($scope.content.length / $scope.count);
            },
            $scope.handleSort = function (field) {
                if ($scope.sortable.indexOf(field) > -1) { return true; } else { return false; }
            };
            $scope.order = function(predicate, reverse) {
                $scope.predicate = predicate;
            };
            $scope.order($scope.sortable[0],false);
            $scope.getNumber = function (num) {
                            return new Array(num);
            };
            $scope.goToPage = function (page) {
              $scope.tablePage = page;
            };
    }
})();