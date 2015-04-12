package com.simon.d3carto.batch.tasklet;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.simon.d3carto.config.D3CartoEnvironmentConfig;
import com.simon.d3carto.domain.api.D3jsFlatNodeJson;
import com.simon.d3carto.domain.api.D3jsNode;
import com.simon.d3carto.domain.conf.App;
import com.simon.d3carto.domain.conf.Database;
import com.simon.d3carto.domain.conf.SimpleApp;
import com.simon.d3carto.domain.node.ApplicationNode;
import com.simon.d3carto.domain.node.DatabaseNode;

/**
 * 
 * @since 11 avr. 2015
 * @author simon 
 */
@Component("transformDatasTasklet")
@Scope("step")
@Slf4j
public class TransformDatasTasklet implements Tasklet {

	@Autowired
	private D3CartoEnvironmentConfig d3CartoEnvironmentConfig;
	
	@Autowired
	private Environment environment;
	
	@Override
	public RepeatStatus execute(StepContribution contribution,
			ChunkContext chunkContext) throws Exception {
		log.info("[Tasklet] NodeItemProcessor.execute() is starting...");
		
		ExecutionContext ecJob = chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext();
		App mainApp = (App) ecJob.get("environment.app");
		ApplicationNode appNode = convertApp(mainApp, d3CartoEnvironmentConfig, environment);
		
		ecJob.put("environment.appNode", appNode);
		
		log.info("[Tasklet] NodeItemProcessor.execute() is finished.");
		return RepeatStatus.FINISHED;
	}
	
	/***************************/
	/***    P R I V A T E    ***/
	/***************************/
	
	/**
	 * From an App object, which represents all environment
	 * create an ApplicationNode. ApplicationNode then can be used 
	 * @param app
	 * @param config
	 * @param environment
	 * @return
	 */
	private static ApplicationNode convertApp(App app, D3CartoEnvironmentConfig config, Environment environment) {
		// Transform database into DatabaseNode
		List<DatabaseNode> databases = Lists.newArrayList();
		app.getDatabases().forEach(db -> databases.add(convert(db, environment)));
		
		// Transform app into ApplicationNode
		if(app.getLinkedApps() != null && !app.getLinkedApps().isEmpty()) {
			
			List<ApplicationNode> leafsTmp = Lists.newArrayList();
			app.getLinkedApps().forEach(a -> 
				leafsTmp.add(
					convertApp(findLinkedAppFromSimpleApp(a, config, environment),
					config,
					environment)));
			// FIXME modifier la creation du POJO
			
//			return new ApplicationNode(
//					environment.getProperty(app.getApplicationName()), 
//					environment.getProperty(app.getApplicationHost()),
//					leafsTmp,
//					databases);
			
		}

		// FIXME modifier la creation du pojo
		// return ApplcationNode with empty leafs
//		return new ApplicationNode(
//				environment.getProperty(app.getApplicationName()), 
//				environment.getProperty(app.getApplicationHost()),
//				null,
//				databases);
		
		return null;
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
	
	private static DatabaseNode convert(Database db, Environment environment) {
		return new DatabaseNode(environment.getProperty(db.getUser()), environment.getProperty(db.getHost()));
	}
	
	private static List<D3jsFlatNodeJson> convertToFlatNode(D3jsNode node, D3jsNode parentNode) {
		
		if(node.hasChildren()) {
			List<D3jsFlatNodeJson> leafsFlatNodes = Lists.newArrayList();
			List<D3jsNode> leafsNodes = node.getChildren();
			leafsNodes.forEach(n -> leafsFlatNodes.addAll(convertToFlatNode(n, node)));
			if(parentNode != null) {
				D3jsFlatNodeJson d3jsFlatNodeJson = new D3jsFlatNodeJson(); 
				d3jsFlatNodeJson.setSource(parentNode.getNodeName());
				d3jsFlatNodeJson.setSource(node.getNodeName());
				leafsFlatNodes.add(d3jsFlatNodeJson);
			}
			return leafsFlatNodes;
		}
		
		D3jsFlatNodeJson d3jsFlatNodeJson = new D3jsFlatNodeJson(); 
		d3jsFlatNodeJson.setSource(parentNode.getNodeName());
		d3jsFlatNodeJson.setSource(node.getNodeName());
		
		return Lists.newArrayList(d3jsFlatNodeJson);
	}
}
