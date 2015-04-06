package com.simon.d3carto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.SpringApplicationConfiguration;

import com.simon.d3carto.config.D3CartoConfig;

/**
 * 
 * @since 5 avr. 2015
 * @author simon
 */
@SpringBootApplication
@SpringApplicationConfiguration(classes=D3CartoConfig.class)
public class D3CartoApplication {

	public static void main(String[] args) {
		SpringApplication.run(D3CartoApplication.class, args);
	}
}
