package com.simon.d3carto.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.simon.d3carto.domain.Environment;
import com.simon.d3carto.domain.api.D3jsFlatNodeJson;
import com.simon.d3carto.domain.api.D3jsNode;
import com.simon.d3carto.domain.api.D3jsTreeNodeJson;
import com.simon.d3carto.domain.node.ApplicationNode;
import com.simon.d3carto.repositories.ApplicationNodeRepositories;

/**
 * 
 * @since 5 avr. 2015
 * @author simon 
 */
@RestController
public class D3CartoRestApi {

//	@Autowired
//	private D3CartoEnvironmentConfig d3CartoEnvironmentConfig;
//	
//	@Autowired
//	private Environment environment;
	
	@Autowired
	private ApplicationNodeRepositories applicationNodeRepositories;
	
	@RequestMapping("/api/hello")
	public String getHelloWorldMessage() {
		return "Hello World! D3JS Cartography is on the way ;)";
	}
	
	@RequestMapping(value="/api/carto", method=RequestMethod.GET)
	public D3jsFlatNodeJson carto() {
		ApplicationNode appNode = applicationNodeRepositories.findFirstByEnvironmentOrderByIdDesc(Environment.DEV1);
		return convertToFlatNode(appNode, null);
	}
	
	private static List<D3jsFlatNodeJson> convertToFlatNode(ApplicationNode node, ApplicationNode parentNode) {
		
		if((node.getDatabases() != null && !node.getDatabases().isEmpty())
				|| (node.getLinkedApps() != null && !node.getLinkedApps().isEmpty())) {
			List<D3jsFlatNodeJson> leafsFlatNodes = Lists.newArrayList();
			List<ApplicationNode> leafsNodes = node.getLinkedApps();
			leafsNodes.forEach(n -> leafsFlatNodes.addAll(convertToFlatNode(n, node)));
			if(parentNode != null) {
				D3jsFlatNodeJson d3jsFlatNodeJson = new D3jsFlatNodeJson(); 
				d3jsFlatNodeJson.setSource(parentNode.getName());
				d3jsFlatNodeJson.setTarget(node.getName());
				leafsFlatNodes.add(d3jsFlatNodeJson);
			}
			return leafsFlatNodes;
		}
		
		D3jsFlatNodeJson d3jsFlatNodeJson = new D3jsFlatNodeJson(); 
		d3jsFlatNodeJson.setSource(parentNode.getName());
		d3jsFlatNodeJson.setTarget(node.getName());
		
		return Lists.newArrayList(d3jsFlatNodeJson);
	}
	
}
