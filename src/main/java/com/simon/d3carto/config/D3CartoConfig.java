package com.simon.d3carto.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 
 * @since 6 avr. 2015
 * @author simon
 */
@Configuration
@ComponentScan("com.simon.d3carto")
@PropertySource(
	value={
		"classpath:/config/environment-dev.properties", 
		"classpath:/config/d3carto-app.properties"})
//@ContextConfiguration(locations={"classpath:/spring/d3carto-application-context.xml"})
@ImportResource("classpath:/spring/d3carto-application-context.xml")
@EnableScheduling
public class D3CartoConfig {

}
