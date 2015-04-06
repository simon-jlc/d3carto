package com.simon.d3carto;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.simon.d3carto.config.D3CartoEnvironmentConfig;
import com.simon.d3carto.config.D3CartoTestConfig;

/**
 * 
 * @since 4 avr. 2015
 * @author simon 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=D3CartoTestConfig.class)
public class AbstractSpringTest {

	@Autowired
    private ApplicationContext applicationContext;

	@Autowired
	protected D3CartoEnvironmentConfig d3CartoEnvironmentConfig;
	
	@Test
	public void testConfig() {
		assertNotNull(applicationContext);
		assertNotNull(d3CartoEnvironmentConfig);
	}
}
