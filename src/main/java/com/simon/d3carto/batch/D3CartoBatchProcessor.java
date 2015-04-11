package com.simon.d3carto.batch;

import java.util.List;

import com.google.common.collect.Lists;
import com.simon.d3carto.domain.api.D3jsNode;
import com.simon.d3carto.domain.api.D3jsTreeNodeJson;

/**
 * 
 * @since 5 avr. 2015
 * @author simon 
 */
public class D3CartoBatchProcessor {
	
	/**
	 * Enable to convert a D3jsNode into D3jsNodeJson to Jacksonify it.
	 * @param node
	 * @return
	 */
	public static D3jsTreeNodeJson convertToTreeJson(D3jsNode node) {
		
		if(node.hasChildren()) {
			List<D3jsTreeNodeJson> leafsTmp = Lists.newArrayList();
			List<D3jsNode> leafsNodes = node.getChildren();
			leafsNodes.forEach(n -> leafsTmp.add(convertToTreeJson(n)));
			return new D3jsTreeNodeJson(node.getNodeName(), leafsTmp);
		} else {
			return new D3jsTreeNodeJson(node.getNodeName(), null);
		}
	}
	
}
