(function () {
    'use strict';

    angular.module('Genome.theme.components')
        .controller('searchbarCtrl', searchbarCtrl);
    searchbarCtrl.$inject = ['$rootScope', '$scope', '$state', '$location', 'toastr', 'DOMAIN_URL'];
    /** @ngInject */
    function searchbarCtrl($rootScope, $scope, $state, $location, toastr) {
    }

})();