(function () {
  'use strict';

  	angular.module('Genome.theme.filters', [])
  		.filter('imageUrl', imageUrl);

  	/** @ngInject */
	function imageUrl(DOMAIN_URL){
	    return function(image) {
	      	if (image) {
	      		return DOMAIN_URL + '/api/' + image;
	      	}
	      	return '/assets/img/default-placeholder.png';
	    };
  	};

})();
