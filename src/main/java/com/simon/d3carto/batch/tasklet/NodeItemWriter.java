package com.simon.d3carto.batch.tasklet;

import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.simon.d3carto.domain.node.ApplicationNode;
import com.simon.d3carto.repositories.ApplicationNodeRepositories;

/**
 * 
 * @since 11 avr. 2015
 * @author simon
 */
@Component("nodeItemWriter")
@Scope("step")
@Slf4j
public class NodeItemWriter implements Tasklet {

	@Autowired
	protected ApplicationNodeRepositories applicationNodeRepositories; 
	
	@Override
	public RepeatStatus execute(StepContribution contribution,
			ChunkContext chunkContext) throws Exception {
		log.info("[Tasklet] NodeItemWriter.execute() is starting...");

		ApplicationNode appNode = (ApplicationNode) chunkContext
				.getStepContext().getJobExecutionContext()
				.get("environment.appNode");

		log.info("[Tasklet] appNode.environment={} ", appNode.getEnvironment());
		log.info("[Tasklet] appNode.name={} ", appNode.getName());
		log.info("[Tasklet] appNode.databases.size={} ", appNode.getDatabases()
				.size());
		log.info("[Tasklet] appNode.applications.size={} ", appNode
				.getLinkedApps().size());
		
		applicationNodeRepositories.save(appNode);

		return RepeatStatus.FINISHED;
	}
}
