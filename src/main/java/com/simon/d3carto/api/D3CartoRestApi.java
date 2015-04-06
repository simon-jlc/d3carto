package com.simon.d3carto.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.simon.d3carto.batch.D3CartoBatchProcessor;
import com.simon.d3carto.batch.D3jsNodeJson;
import com.simon.d3carto.domain.D3jsNode;

/**
 * 
 * @since 5 avr. 2015
 * @author simon 
 */
@RestController
public class D3CartoRestApi {

	@RequestMapping("/api/hello")
	public String getHelloWorldMessage() {
		return "Hello World! D3JS Cartography is on the way ;)";
	}
	
	@RequestMapping(value="/api/carto", method=RequestMethod.GET)
	public D3jsNodeJson carto() {
		D3jsNode d3jsNode = D3CartoBatchProcessor.refresh();
		return D3CartoBatchProcessor.convert(d3jsNode);
	}
}
