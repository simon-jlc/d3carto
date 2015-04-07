/**
 * New node file
 */
(function() {

	// no dependancies
	var d3carto = angular.module(
			'd3carto-app', 
			['d3carto-graph2']); // dependcies
	
	d3carto.controller('D3CartoController', ['$scope', '$http', '$log', function($scope, $http, $log) {
		
		$log.info("Initialize D3CartoController...");
		//		$scope.carto();
		
	}]);

})();