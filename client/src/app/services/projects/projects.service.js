(function () {
    'use strict';

    angular
        .module('Genome')
        .factory('ProjectsService', ProjectsService);

    ProjectsService.$inject = ['$http', 'backendService'];
    function ProjectsService($http, backendService) {
        var service = {};

        service.findAllStudies = findAllStudies;

        return service;

        function findAllStudies(page, size, orderBy, isAsc, callback) {
            var orderType = isAsc ? "asc" : "desc";        
            $http({
                method: "GET",
                url: backendService.serviceUrl
                    +   backendService.baseUrl
                    +   '/studies',
                params: {
                    sort: orderBy + "," + orderType,
                    page: page,
                    size: size
                },
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
