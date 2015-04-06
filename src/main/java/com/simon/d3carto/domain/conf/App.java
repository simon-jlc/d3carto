package com.simon.d3carto.domain.conf;

import java.util.List;

import lombok.Data;

/**
 * 
 * @since 6 avr. 2015
 * @author simon 
 */
@Data
public class App {
	private String propertySrcPath;
	private String propertySrcType;
	private String applicationName;
	private String applicationHost;
	
	private List<SimpleApp> linkedApps;
	private List<Database> databases;
}
