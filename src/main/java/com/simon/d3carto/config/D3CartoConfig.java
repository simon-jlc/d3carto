package com.simon.d3carto.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 
 * @since 6 avr. 2015
 * @author simon 
 */
@Configuration
@ComponentScan("com.simon.d3carto")
@PropertySource("classpath:/config/environment-dev.properties")
public class D3CartoConfig {

}
