package com.simon.d3carto.domain.node;

import java.util.List;

import lombok.Data;

import com.simon.d3carto.domain.Environment;

/**
 * 
 * @since 5 avr. 2015
 * @author simon 
 */
@Data
public abstract class AbstractNode {
	
	private Environment environment;
	
	public abstract String getName();
	
	public abstract boolean hasChildren();
		
	public abstract List<AbstractNode> getChildren();
	
}
