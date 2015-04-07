package com.simon.d3carto.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.simon.d3carto.batch.D3CartoBatchProcessor;
import com.simon.d3carto.config.D3CartoEnvironmentConfig;
import com.simon.d3carto.domain.api.D3jsFlatNodeJson;
import com.simon.d3carto.domain.api.D3jsTreeNodeJson;
import com.simon.d3carto.domain.node.D3jsNode;

/**
 * 
 * @since 5 avr. 2015
 * @author simon 
 */
@RestController
public class D3CartoRestApi {

	@Autowired
	private D3CartoEnvironmentConfig d3CartoEnvironmentConfig;
	
	@Autowired
	private Environment environment;
	
	@RequestMapping("/api/hello")
	public String getHelloWorldMessage() {
		return "Hello World! D3JS Cartography is on the way ;)";
	}
	
	@RequestMapping(value="/api/carto", method=RequestMethod.GET)
	public D3jsTreeNodeJson carto() {
		D3jsNode d3jsNode = D3CartoBatchProcessor.refresh(d3CartoEnvironmentConfig, environment);
		return D3CartoBatchProcessor.convertToTreeJson(d3jsNode);
	}
	
	@RequestMapping(value="/api/carto2", method=RequestMethod.GET)
	public List<D3jsFlatNodeJson> carto2() {
		D3jsNode d3jsNode = D3CartoBatchProcessor.refresh(d3CartoEnvironmentConfig, environment);
		return D3CartoBatchProcessor.convertToFlatNode(d3jsNode, null);
	}
	
}
