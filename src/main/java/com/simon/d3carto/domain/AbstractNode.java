package com.simon.d3carto.domain;

import lombok.Data;

/**
 * 
 * @since 5 avr. 2015
 * @author simon 
 */
@Data
public abstract class AbstractNode implements D3jsNode {
	private Environment environment;
	
	@Override
	public Integer getSize() {
		return null;
	}
}
