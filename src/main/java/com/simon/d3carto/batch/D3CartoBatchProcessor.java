package com.simon.d3carto.batch;

import java.util.List;

import org.springframework.core.env.Environment;

import com.google.common.collect.Lists;
import com.simon.d3carto.config.D3CartoEnvironmentConfig;
import com.simon.d3carto.domain.api.D3jsNodeJson;
import com.simon.d3carto.domain.conf.App;
import com.simon.d3carto.domain.conf.Database;
import com.simon.d3carto.domain.conf.SimpleApp;
import com.simon.d3carto.domain.node.ApplicationNode;
import com.simon.d3carto.domain.node.D3jsNode;
import com.simon.d3carto.domain.node.DatabaseNode;

/**
 * 
 * @since 5 avr. 2015
 * @author simon 
 */
public class D3CartoBatchProcessor {
	
	public static D3jsNode refresh(D3CartoEnvironmentConfig config, Environment environment) {
		List<DatabaseNode> dbsNode = null;
		List<ApplicationNode> appsNode = null;
		
		App mainApp = config.getMainApplication();
		
		// evaluate properties
		List<Database> mainAppDbs = mainApp.getDatabases();
		for(Database db : mainAppDbs) {
			if(dbsNode == null) {
				dbsNode = Lists.newArrayList();
			}
			
			String user = environment.getProperty(db.getUser());
			String host = environment.getProperty(db.getHost());
			dbsNode.add(new DatabaseNode(user, host));
		}
		
		List<SimpleApp> simplesApp = mainApp.getLinkedApps();
		for(SimpleApp simpleApp : simplesApp) {
			if(appsNode == null) {
				appsNode = Lists.newArrayList();
			}
			
			// TODO : implement sub link
//			appsNode.add(new ApplicationNode(, server, linkedApps, databases))
		}
		
		String mainApplicationName = environment.getProperty(mainApp.getApplicationName());
		String mainApplicationHost = environment.getProperty(mainApp.getApplicationHost());
		
		return new ApplicationNode(mainApplicationName, mainApplicationHost, appsNode, dbsNode);
	}
	
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
