/**
 * Application entry point.
 * D3js + Angular base on following article: http://briantford.com/blog/angular-d3
 * 
 */
(function() {

	// no dependancies
	var d3carto = angular.module(
			'd3carto-app', 
			[ 'd3carto-directives' ]); // dependcies
	
	d3carto.controller('D3CartoController', ['$scope', '$http', '$log', function($scope, $http, $log) {
		
		$log.info("Initialize D3CartoController...");
		
		$scope.carto2 = function() {
			// do the asynch request
			$http({
				url: 'api/carto2',
				method: 'GET',
				headers: {
					"Content-type":"application/json"
				},
				responseType: 'json'
			}).success(function(datas) {
				$log.info("total transition=" + datas.length);
				$log.info("-----------------");
				$log.info("Starting initializa D3JS graph...");
				
				$scope.data = datas;
			}).error(function(data) {
				// update alert msg with danger type
				// TODO do somethng when error
			
			});
		};
		
		$scope.carto2();
	}]);
})();