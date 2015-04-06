package com.simon.d3carto.carto;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 
 * @since 6 avr. 2015
 * @author simon 
 */
@Data
@Configuration
@Component
@ConfigurationProperties(prefix="environment", locations="/config/d3carto-test.yaml")
public class D3CartoEnvironmentConfig {
	public String mainApplication;
	public String otherApplication;
	public App app;
}
