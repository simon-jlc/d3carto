package com.simon.d3carto.carto;

import java.util.List;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.simon.d3carto.domain.conf.App;
import com.simon.d3carto.domain.conf.SimpleApp;

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
	public App mainApplication;
	public List<SimpleApp> otherApplication;
}
