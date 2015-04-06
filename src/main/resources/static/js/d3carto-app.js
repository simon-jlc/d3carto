/**
 * New node file
 */
(function() {

	// no dependancies
	var d3carto = angular.module('d3carto', []);
	
	d3carto.controller('D3CartoController', ['$scope', '$http', '$log', function($scope, $http, $log) {
		
		var d3ctrl = this;
		d3ctrl.masterNode;
		
		$log.info("Initialize D3CartoController...");
		
		$scope.carto = function() {
//			oc.loading = 1;
//			oc.categories = [];
//			
			// do the asynch request
			$http({
				url: 'api/carto',
				method: 'GET',
				headers: {
					"Content-type":"application/json"
				},
				responseType: 'json'
			}).success(function(data) {
				$log.info("name=" + data.name);
				$log.info("children=" + data.children.length);
				$log.info("-----------------");
				
			}).error(function(data) {
				// update alert msg with danger type
				// TODO do somethng when error
			
			});
		};
		
//		$scope.carto();
	}]);

})();