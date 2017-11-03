(function () {
    'use strict';

    angular
        .module('Genome')
        .factory('ProjectsService', ProjectsService);
    
    /* @ngInject */
    function ProjectsService($http, backendService, LoadingService) {
        var service = {};

        service.findAllStudies = findAllStudies;

        return service;

        function findAllStudies(searchBy, keyword, page, size, orderBy, isAsc, callback) {
            var orderType = isAsc ? "asc" : "desc";
            page = parseInt(page) - 1;
            LoadingService.startLoading();
            var parameters = {
                sort: orderBy + "," + orderType,
                page: page,
                size: size
            };

            if (keyword != "") {
                parameters[searchBy] = keyword;
            }

            $http({
                method: "GET",
                url: backendService.serviceUrl
                    +   backendService.baseUrl
                    +   '/studies',
                params: parameters,
                headers:{
                    'Content-Type':'application/json; charset=UTF-8'
                },
                contentType: "application/json"                
            })
            .then(function (response) {
                LoadingService.stopLoading();
                callback({
                    success: true,
                    data: response.data,
                    status: response.status
                });
            }, function () {
                LoadingService.stopLoading();
                callback({
                    success: false,
                    message: 'Failed to retrieve list of projects'
                });
            });
        }
    }

})();
