package com.simon.d3carto.repositories;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.simon.d3carto.domain.node.ApplicationNode;

/**
 * 
 * @since 11 avr. 2015
 * @author simon 
 */
public interface ApplicationNodeRepositories extends ElasticsearchRepository<ApplicationNode, String> {

}
