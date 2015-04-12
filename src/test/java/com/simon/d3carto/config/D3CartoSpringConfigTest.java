package com.simon.d3carto.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;

import com.simon.d3carto.D3CartoApplication;
import com.simon.d3carto.batch.D3CartoJobLauncher;

/**
 * 
 * @since 6 avr. 2015
 * @author simon 
 */
@Configuration
@ComponentScan(basePackages="com.simon.d3carto", 
	excludeFilters={
		@ComponentScan.Filter(value=D3CartoApplication.class, type=FilterType.ASSIGNABLE_TYPE),
		@ComponentScan.Filter(value=D3CartoConfig.class, type=FilterType.ASSIGNABLE_TYPE),
		@ComponentScan.Filter(value=D3CartoJobLauncher.class, type=FilterType.ASSIGNABLE_TYPE)
	})
@PropertySource("classpath:/config/environment-dev-test.properties")
@EnableConfigurationProperties(D3CartoEnvironmentConfig.class) // Initialize an instance from @ConfigurationProperties file
public class D3CartoSpringConfigTest {
}
