package com.simon.d3carto.domain.node;

import java.util.List;

/**
 * 
 * @since 5 avr. 2015
 * @author simon 
 */
public interface D3jsNode {

	public String getNodeName();
	
	public Integer getSize();
	
	public List<D3jsNode> getChildren();
	
	public boolean hasChildren();
}
