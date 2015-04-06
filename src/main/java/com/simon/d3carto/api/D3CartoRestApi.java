package com.simon.d3carto.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.simon.d3carto.batch.D3CartoBatchProcessor;
import com.simon.d3carto.config.D3CartoEnvironmentConfig;
import com.simon.d3carto.domain.api.D3jsNodeJson;
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
	public D3jsNodeJson carto() {
		D3jsNode d3jsNode = D3CartoBatchProcessor.refresh(d3CartoEnvironmentConfig, environment);
		return D3CartoBatchProcessor.convert(d3jsNode);
	}
}
