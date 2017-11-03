(function () {
    'use strict';

    angular.module('Genome.theme.components')
        .controller('navigationCtrl', navigationCtrl);
        navigationCtrl.$inject = ['$rootScope', '$scope', '$location', '$http'];
    /** @ngInject */
    function navigationCtrl($rootScope, $scope, $location, $http) {
		$scope.searchTypes = [
			{
				name: "Study Type",
				field: "studyType"
			},
			{
				name: "Taxonomy Id",
				field: "taxonomyId"
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
    }

})();