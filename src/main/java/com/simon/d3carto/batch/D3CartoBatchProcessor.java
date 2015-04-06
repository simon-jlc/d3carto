package com.simon.d3carto.batch;

import java.util.List;

import org.springframework.core.env.Environment;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
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
	
	/**
	 * Refresh D3jsNode from D3CartoEnvironmentConfig
	 * @param config: describe all linked between environments
	 * @param environment: contains properties file
	 * @return
	 */
	public static D3jsNode refresh(D3CartoEnvironmentConfig config, Environment environment) {
		return convert(config.getMainApplication(), config, environment);
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
	
	/***************************/
	/***    P R I V A T E    ***/
	/***************************/
	private static DatabaseNode convert(Database db, Environment environment) {
		return new DatabaseNode(environment.getProperty(db.getUser()), environment.getProperty(db.getHost()));
	}
	
	private static App findLinkedAppFromSimpleApp(SimpleApp simpleApp, D3CartoEnvironmentConfig config, Environment environment) {
		String keyPropertyName = simpleApp.getPropertiesName();
		return Iterables.find(config.getOtherApplication(), new Predicate<App>() {
			@Override
			public boolean apply(App app) {
				return keyPropertyName.equals(app.getApplicationHost());
			}
		});
	}
	
	private static ApplicationNode convert(App app, D3CartoEnvironmentConfig config, Environment environment) {
		
		// Transform database into DatabaseNode
		List<DatabaseNode> databases = Lists.newArrayList();
		app.getDatabases().forEach(db -> databases.add(convert(db, environment)));
		
		// Transform app into ApplicationNode
		if(app.getLinkedApps() != null && !app.getLinkedApps().isEmpty()) {
			
			List<ApplicationNode> leafsTmp = Lists.newArrayList();
			app.getLinkedApps().forEach(a -> 
				leafsTmp.add(
					convert(findLinkedAppFromSimpleApp(a, config, environment),
					config,
					environment)));
			
			return new ApplicationNode(
					environment.getProperty(app.getApplicationName()), 
					environment.getProperty(app.getApplicationHost()),
					leafsTmp,
					databases);
		}
		
		// return ApplcationNode with empty leafs
		return new ApplicationNode(
				environment.getProperty(app.getApplicationName()), 
				environment.getProperty(app.getApplicationHost()),
				null,
				databases);
	}
	
}
