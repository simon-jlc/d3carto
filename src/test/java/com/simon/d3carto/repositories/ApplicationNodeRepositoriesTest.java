package com.simon.d3carto.repositories;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.google.common.collect.Lists;
import com.simon.d3carto.AbstractSpringTest;
import com.simon.d3carto.domain.Environment;
import com.simon.d3carto.domain.node.ApplicationNode;
import com.simon.d3carto.domain.node.DatabaseNode;

@ContextConfiguration(locations="classpath:spring/d3carto-elasticsearch-test.xml")
public class ApplicationNodeRepositoriesTest extends AbstractSpringTest {

    @Resource
    private ApplicationNodeRepositories applicationNodeRepositories;

    private ApplicationNode masterNodeDev1;

	private ApplicationNode masterNodeDev2;
    
    @Before
    public void init(){
    	applicationNodeRepositories.deleteAll();
    	
    	DatabaseNode dbApp1Dev1 = new DatabaseNode("root", "srvdevdb01");
		ApplicationNode app1Dev1 = new ApplicationNode("123", "Application 01", "srvdevapp01", null, Lists.newArrayList(dbApp1Dev1));
		DatabaseNode dbApp2Dev1 = new DatabaseNode("root", "srvdevdb02");
		this.masterNodeDev1 = new ApplicationNode("111", "Application 02", "srvdevapp02", Lists.newArrayList(app1Dev1), Lists.newArrayList(dbApp1Dev1, dbApp2Dev1));
		
		DatabaseNode dbApp1Dev2 = new DatabaseNode("root", "srvdev2db01");
		ApplicationNode app1Dev2 = new ApplicationNode("223", "Application 01", "srvdev2app01", null, Lists.newArrayList(dbApp1Dev2));
		DatabaseNode dbApp2Dev2 = new DatabaseNode("root", "srvdev2db02");
		this.masterNodeDev2 = new ApplicationNode("211", "Application 02", "srvdev2app02", Lists.newArrayList(app1Dev2), Lists.newArrayList(dbApp1Dev2, dbApp2Dev2));
		
		// add environment
		dbApp1Dev1.setEnvironment(Environment.DEV1);
		dbApp2Dev1.setEnvironment(Environment.DEV1);
		app1Dev1.setEnvironment(Environment.DEV1);
		masterNodeDev1.setEnvironment(Environment.DEV1);
		
		dbApp1Dev2.setEnvironment(Environment.DEV2);
		dbApp2Dev2.setEnvironment(Environment.DEV2);
		app1Dev2.setEnvironment(Environment.DEV2);
		masterNodeDev2.setEnvironment(Environment.DEV2);
    }

    @Test
    public void shouldIndexOneDocument(){
 
    	//Indexing using sampleArticleRepository
        applicationNodeRepositories.save(masterNodeDev1);
        //lets try to search same record in elasticsearch
        ApplicationNode indexedMasterNode = applicationNodeRepositories.findOne(masterNodeDev1.getId());
        
        assertThat(indexedMasterNode,is(notNullValue()));
        assertThat(indexedMasterNode.getId(),is(masterNodeDev1.getId()));
    }
    
    @Test
    public void findFirstByEnvironmentOrderByIdDescTest() {
    	//Indexing using sampleArticleRepository
        applicationNodeRepositories.save(masterNodeDev1);
        applicationNodeRepositories.save(masterNodeDev2);
        
        ApplicationNode appNodeResult = applicationNodeRepositories.findFirstByEnvironmentOrderByIdDesc(Environment.DEV2);
        assertThat(appNodeResult,is(notNullValue()));
        assertThat(appNodeResult.getId(),is(masterNodeDev1.getId()));
    }
}
