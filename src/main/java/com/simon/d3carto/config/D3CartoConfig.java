package com.simon.d3carto.config;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.test.context.ContextConfiguration;

/**
 * 
 * @since 6 avr. 2015
 * @author simon
 */
@Configuration
@ComponentScan("com.simon.d3carto")
@PropertySource("classpath:/config/environment-dev.properties")
@ContextConfiguration(locations={"classpath:/spring/d3carto-application-context.xml"})
@EnableBatchProcessing
@EnableScheduling
public class D3CartoConfig {

}
