(function () {
    'use strict';

    angular
        .module('Genome')
        .factory('LoadingService', LoadingService);

    function LoadingService($mdDialog) {
        var service = {};

        service.startLoading = startLoading;
        service.stopLoading = stopLoading;

        return service;

        function startLoading() {
            $mdDialog.show({
                template: '<md-dialog id="plz_wait" style="box-shadow:none">' +
                    '<md-dialog-content layout="row" layout-margin layout-padding layout-align="center center" aria-label="wait">' +
                    '<md-progress-circular id="circular" class="md-primary md-hue-3" md-mode="indeterminate" md-diameter="50"></md-progress-circular>' +
                    'Loading...' +
                    '</md-dialog-content>' +
                    '</md-dialog>',
                parent: angular.element(document.body),
                clickOutsideToClose: false,
                fullscreen: false,
                escapeToClose: false
            });
        }

        function stopLoading() {
            $mdDialog.cancel();
        }
    }

})();
