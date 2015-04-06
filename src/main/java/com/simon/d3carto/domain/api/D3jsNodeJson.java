package com.simon.d3carto.domain.api;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 
 * @since 5 avr. 2015
 * @author simon 
 */

@Data
@AllArgsConstructor
public class D3jsNodeJson {
	
	private String name;
	
	private List<D3jsNodeJson> children;
	
}
