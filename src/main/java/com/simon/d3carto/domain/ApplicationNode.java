package com.simon.d3carto.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.google.common.collect.Lists;

/**
 * 
 * @since 5 avr. 2015
 * @author simon 
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class ApplicationNode extends AbstractNode {
	
	private String name;
	private String server;
	private List<ApplicationNode> linkedApps;
	private List<DatabaseNode> databases;
	
	@Override
	public String getNodeName() {
		return name;
	}
	
	@Override
	public List<D3jsNode> getChildren() {
		List<D3jsNode> children = Lists.newArrayList();
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
