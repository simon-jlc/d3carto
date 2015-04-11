package com.simon.d3carto.domain.conf;

import java.util.Map;

import lombok.Data;

/**
 * Represents In Memory all properties of Application
 * Initialize from configuration file retrieved
 * @since 11 avr. 2015
 * @author simon 
 */
@Data
public class AppProperties {
	
	private Map<String, String> properties;
	
}
