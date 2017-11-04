(function () {
    'use strict';

    angular
        .module('Genome')
        .factory('TaxonomyService', TaxonomyService);
    
    /* @ngInject */
    function TaxonomyService($http, backendService, LoadingService) {
        var service = {};

        service.findAllTaxonomy = findAllTaxonomy;        

        return service;

        function findAllTaxonomy(callback) {            
            $http({
                method: "GET",
                url: backendService.serviceUrl
                    +   backendService.baseUrl
                    +   '/taxonomy',                
                headers:{
                    'Content-Type':'application/json; charset=UTF-8'
                },
                contentType: "application/json"                
            })
            .then(function (response) {                
                callback({
                    success: true,
                    data: response.data,
                    status: response.status
                });
            }, function () {                
                callback({
                    success: false,
                    message: 'Failed to retrieve list of projects'
                });
            });
        }
    }

})();
