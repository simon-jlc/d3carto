package com.simon.d3carto.batch;

import java.util.List;

import com.google.common.collect.Lists;
import com.simon.d3carto.domain.api.D3jsNodeJson;
import com.simon.d3carto.domain.node.D3jsNode;

/**
 * 
 * @since 5 avr. 2015
 * @author simon 
 */
public class D3CartoBatchProcessor {
	
	public static D3jsNode refresh() {
		
		
		return null;
	}
	
//	public static D3jsNode refresh() {
//		
//		DatabaseNode dbApp1 = new DatabaseNode("root", "srvdevdb01");
//		ApplicationNode app1 = new ApplicationNode("Application 01", "srvdevapp01", null, Lists.newArrayList(dbApp1));
//		
//		DatabaseNode dbApp2 = new DatabaseNode("root", "srvdevdb02");
//		ApplicationNode app2 = new ApplicationNode("Application 02", "srvdevapp02", Lists.newArrayList(app1), Lists.newArrayList(dbApp1, dbApp2));
//		
//		return app2;
//	}
	
	/**
	 * Enable to convert a D3jsNode into D3jsNodeJson to Jacksonify it.
	 * @param node
	 * @return
	 */
	public static D3jsNodeJson convert(D3jsNode node) {
		
		if(node.hasChildren()) {
			List<D3jsNodeJson> leafsTmp = Lists.newArrayList();
			List<D3jsNode> leafsNodes = node.getChildren();
			leafsNodes.forEach(n -> leafsTmp.add(convert(n)));
			return new D3jsNodeJson(node.getNodeName(), leafsTmp);
		} else {
			return new D3jsNodeJson(node.getNodeName(), null);
		}
	}
}
