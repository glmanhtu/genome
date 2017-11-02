(function () {
    'use strict';
    angular
        .module('Genome', [
        	'ngMaterial',
            'toastr',
            'ui.router',
            'bw.paging',
            'Genome.pages',
            'Genome.theme'
        ])
        .run(run);

    function run($rootScope, $location, $http, toastr) {
    }    
})();
