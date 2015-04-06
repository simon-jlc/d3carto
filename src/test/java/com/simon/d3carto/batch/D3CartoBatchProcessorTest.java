package com.simon.d3carto.batch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.google.common.collect.Lists;
import com.simon.d3carto.AbstractSpringTest;
import com.simon.d3carto.domain.api.D3jsNodeJson;
import com.simon.d3carto.domain.node.ApplicationNode;
import com.simon.d3carto.domain.node.D3jsNode;
import com.simon.d3carto.domain.node.DatabaseNode;


/**
 * 
 * @since 6 avr. 2015
 * @author simon 
 */
public class D3CartoBatchProcessorTest extends AbstractSpringTest {
	
	protected D3jsNode masterNode;
	
	@Autowired
	protected Environment environment;
	
	@Before
	public void initMasterNode() {
		DatabaseNode dbApp1 = new DatabaseNode("root", "srvdevdb01");
		ApplicationNode app1 = new ApplicationNode("Application 01", "srvdevapp01", null, Lists.newArrayList(dbApp1));
		DatabaseNode dbApp2 = new DatabaseNode("root", "srvdevdb02");
		masterNode = new ApplicationNode("Application 02", "srvdevapp02", Lists.newArrayList(app1), Lists.newArrayList(dbApp1, dbApp2));
	}

	@Test
	public void testRefresh() {
		
		String app1Name = environment.getProperty("app1.name");
		assertEquals(app1Name, "Application 01");
		
		D3jsNode masterNode = D3CartoBatchProcessor.refresh(d3CartoEnvironmentConfig, environment);
		assertTrue(masterNode.getChildren().size() == 1);
	}
	
	@Test
	public void testConvert () {
		D3jsNodeJson masterNodeJson = D3CartoBatchProcessor.convert(masterNode);
		assertEquals("Application 02", masterNodeJson.getName());
		assertEquals(3, masterNodeJson.getChildren().size());
	}
}
