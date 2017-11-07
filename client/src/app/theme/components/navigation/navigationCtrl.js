(function () {
    'use strict';

    angular.module('Genome.theme.components')
        .controller('navigationCtrl', navigationCtrl);

    /** @ngInject */
    function navigationCtrl($rootScope, $scope, $location, $http, $mdDialog) {

		$scope.searchTypes = [
			{
				name: "Study Type",
				field: "studyType"
			},
			{
				name: "Taxonomy Id",
				field: "taxonomyId"
			},
			{
			    name: "Project Id",
			    field: "projectId"
			},
			{
			    name: "Title",
			    field: "title"
			}
		];
		$rootScope.keyword = "";
		$rootScope.currentSearchType = "taxonomyId";

		$scope.getSearchTypeName = function(searchType) {
			for (var i = $scope.searchTypes.length - 1; i >= 0; i--) {
				if ($scope.searchTypes[i].field == searchType) {
					return $scope.searchTypes[i].name;
				}
			}
		}

		$scope.setCurrentSearchType = function(searchType) {
			$rootScope.currentSearchType = searchType;
		}

		$scope.openMenu = function($mdMenu, ev) {	      	
	      	$mdMenu.open(ev);
	    };

	    $scope.createProject = function(ev) {
	    	$mdDialog.show({
		        locals: {project: null, mode: 1},
		        controller: 'detailsCtrl',
		        templateUrl: 'app/pages/home/details/details.tmpl.html',
		        parent: angular.element(document.body),
		        targetEvent: ev,
		        clickOutsideToClose:true,
		        fullscreen: false
	      	})
	      	.then(function(answer) {
		        $scope.status = 'You said the information was "' + answer + '".';
	      	}, function() {
		        $scope.status = 'You cancelled the dialog.';
	      	});
	    }
    }

})();