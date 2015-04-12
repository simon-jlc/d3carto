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
import com.simon.d3carto.domain.node.ApplicationNode;
import com.simon.d3carto.domain.node.DatabaseNode;

@ContextConfiguration(locations="classpath:spring/d3carto-elasticsearch-test.xml")
public class ApplicationNodeRepositoriesTest extends AbstractSpringTest {

    @Resource
    private ApplicationNodeRepositories applicationNodeRepositories;

    @Before
    public void emptyData(){
    	applicationNodeRepositories.deleteAll();
    }

    @Test
    public void shouldIndexSingleBookEntity(){
 
    	DatabaseNode dbApp1 = new DatabaseNode("root", "srvdevdb01");
		ApplicationNode app1 = new ApplicationNode("123", "Application 01", "srvdevapp01", null, Lists.newArrayList(dbApp1));
		DatabaseNode dbApp2 = new DatabaseNode("root", "srvdevdb02");
		ApplicationNode masterNode = new ApplicationNode("111", "Application 02", "srvdevapp02", Lists.newArrayList(app1), Lists.newArrayList(dbApp1, dbApp2));
    	
    	//Indexing using sampleArticleRepository
        applicationNodeRepositories.save(masterNode);
        //lets try to search same record in elasticsearch
        ApplicationNode indexedMasterNode = applicationNodeRepositories.findOne(masterNode.getId());
        
        assertThat(indexedMasterNode,is(notNullValue()));
        assertThat(indexedMasterNode.getId(),is(masterNode.getId()));
    }
}
