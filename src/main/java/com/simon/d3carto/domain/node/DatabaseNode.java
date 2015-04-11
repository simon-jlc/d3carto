package com.simon.d3carto.domain.node;

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
	
	public DatabaseNode() {}
	
}
