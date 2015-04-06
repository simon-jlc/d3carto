package com.simon.d3carto.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @since 5 avr. 2015
 * @author simon 
 */
@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
public class DatabaseNode extends AbstractNode {

	private String user;
	private String server;
	
	@Override
	public String getNodeName() {
		return user + "@" + server;
	}
	
	@Override
	public List<D3jsNode> getChildren() {
		return null;
	}
	
	@Override
	public boolean hasChildren() {
		return false;
	}
}
