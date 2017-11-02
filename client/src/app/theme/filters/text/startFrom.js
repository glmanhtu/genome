(function () {
  'use strict';

  	angular.module('Genome.theme.filters', [])
  		.filter('startFrom', startFrom);

  	/** @ngInject */
	function startFrom(){
	    return function(input, start) {
	      	start = +start;
            return input.slice(start);
	    };
  	};

})();
