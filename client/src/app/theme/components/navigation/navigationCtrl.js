(function () {
    'use strict';

    angular.module('Genome.theme.components')
        .controller('navigationCtrl', navigationCtrl);
        navigationCtrl.$inject = ['$rootScope', '$scope', '$location', '$http', 'toastr'];
    /** @ngInject */
    function navigationCtrl($rootScope, $scope, $location, $http, toastr) {

    }

})();