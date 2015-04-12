package com.simon.d3carto.domain.node;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import com.google.common.collect.Lists;

/**
 * 
 * @since 5 avr. 2015
 * @author simon 
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Document(indexName = "applications", type = "application", shards = 1, replicas = 0, refreshInterval = "-1", indexStoreType = "memory")
public class ApplicationNode extends AbstractNode {
	
	@Id
	private String id;
	private String name;
	private String server;
	private List<ApplicationNode> linkedApps;
	private List<DatabaseNode> databases;
	
	public ApplicationNode() {}

	@Override
	public List<AbstractNode> getChildren() {
		List<AbstractNode> children = Lists.newArrayList();
		if(databases != null) children.addAll(databases);
		if(linkedApps != null) children.addAll(linkedApps);
		return children;
	}
	
	@Override
	public boolean hasChildren() {
		return (databases != null && !databases.isEmpty())
				|| (linkedApps != null && !linkedApps.isEmpty());
	}
}
