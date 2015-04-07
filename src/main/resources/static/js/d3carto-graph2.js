
(function() {

	// no dependancies
	var d3cartoGraph2 = angular.module('d3carto-graph2', []);
	
	d3cartoGraph2.controller('D3CartoGraph2', ['$scope', '$http', '$log', function($scope, $http, $log) {
		
		$scope.carto2 = function() {
			// do the asynch request
			$http({
				url: 'api/carto2',
				method: 'GET',
				headers: {
					"Content-type":"application/json"
				},
				responseType: 'json'
			}).success(function(links) {
				$log.info("total transition=" + links.length);
				$log.info("-----------------");
				$log.info("Starting initializa D3JS graph...");
				
								
		        var nodes = {};

		        // Compute the distinct nodes from the links.
		        links.forEach(function(link) {
		          link.source = nodes[link.source] || (nodes[link.source] = {name: link.source});
		          link.target = nodes[link.target] || (nodes[link.target] = {name: link.target});
		        });

		        var width = 960,
		            height = 500;

		        var force = d3.layout.force()
		            .nodes(d3.values(nodes))
		            .links(links)
		            .size([width, height])
		            .linkDistance(60)
		            .charge(-300)
		            .on("tick", tick)
		            .start();

		        var svg = d3.select(".svg-container").append("svg")
		            .attr("width", width)
		            .attr("height", height);

		        var link = svg.selectAll(".link")
		            .data(force.links())
		          .enter().append("line")
		            .attr("class", "link");

		        var node = svg.selectAll(".node")
		            .data(force.nodes())
		          .enter().append("g")
		            .attr("class", "node")
		            .on("mouseover", mouseover)
		            .on("mouseout", mouseout)
		            .call(force.drag);

		        node.append("circle")
		            .attr("r", 8);

		        node.append("text")
		            .attr("x", 12)
		            .attr("dy", ".35em")
		            .text(function(d) { return d.name; });
		        
		        function tick() {
					link.attr("x1", function(d) {
						return d.source.x;
					}).attr("y1", function(d) {
						return d.source.y;
					}).attr("x2", function(d) {
						return d.target.x;
					}).attr("y2", function(d) {
						return d.target.y;
					});

					node.attr("transform", function(d) {
						return "translate(" + d.x + "," + d.y + ")";
					});
				}
		        
			}).error(function(data) {
				// update alert msg with danger type
				// TODO do somethng when error
			
			});
		};

		

		var mouseover = function() {
			d3.select(this).select("circle").transition().duration(750)
					.attr("r", 16);
		}

		var mouseout = function() {
			d3.select(this).select("circle").transition().duration(750)
					.attr("r", 8);
		}
        
        // - do call and display carto
        $scope.carto2();
	}]);
})();