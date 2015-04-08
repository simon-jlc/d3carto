/**
 * d3carto-directives is module where all directives are defined.
 * 	-> Separte view/vizualisation from controller
 * 
 * D3js implementation is based on Forced-Directed graph
 *  -> http://bl.ocks.org/mbostock
 */
(function() {

	var d3cartoDirectives = angular.module(
			'd3carto-directives', 
			[ ]);

	d3cartoDirectives.directive('d3cartoVisualization', function() {
		
		var mouseover = function() {
			d3.select(this).select("circle").transition().duration(750)
					.attr("r", 16);
		}

		var mouseout = function() {
			d3.select(this).select("circle").transition().duration(750)
					.attr("r", 8);
		}

		return {
		    restrict: 'E',
		    terminal: true,
		    scope: {
		      val: '=',
		      grouped: '='
		    },
		    link: function (scope, element, attrs) {
		    	var nodes = {};

		    	scope.$watch('val', function (newVal, oldVal) {
		    		
		    		// if 'val' is undefined, exit
		            if (!newVal) {
						return;
					}
		    		
		    		// Compute the distinct nodes from the links.
		    		newVal.forEach(function(link) {
			          link.source = nodes[link.source] || (nodes[link.source] = {name: link.source});
			          link.target = nodes[link.target] || (nodes[link.target] = {name: link.target});
			        });

			        var width = 960,
			            height = 500;

			        var force = d3.layout.force()
			            .nodes(d3.values(nodes))
			            .links(newVal)
			            .size([width, height])
			            .linkDistance(60)
			            .charge(-300)
			            .on("tick", tick)
			            .start();

			        var svg = d3.select(element[0]).append("svg")
			            .attr("width", width)
			            .attr("height", height);

			        var linkElement = svg.selectAll(".link")
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
			        	linkElement.attr("x1", function(d) {
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
		    		
		    	});
		    	
	        }		
		}
	});
	
})();