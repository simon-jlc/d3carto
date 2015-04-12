package com.simon.d3carto.batch.tasklet;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.simon.d3carto.domain.node.ApplicationNode;
import com.simon.d3carto.repositories.ApplicationNodeRepositories;

/**
 * 
 * @since 11 avr. 2015
 * @author simon
 */
@Component("loadDatasTasklet")
@Scope("step")
@Slf4j
public class LoadDatasTasklet implements Tasklet {

	@Resource
	protected ApplicationNodeRepositories applicationNodeRepositories; 
	
	@Override
	public RepeatStatus execute(StepContribution contribution,
			ChunkContext chunkContext) throws Exception {

		ExecutionContext ecJob = chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext();
		ApplicationNode appNode = (ApplicationNode) ecJob.get("environment.appNode");
		if(appNode != null) {
			log.info("Saving current environment config to elasticserch");
			applicationNodeRepositories.save(appNode);
		}

		return RepeatStatus.FINISHED;
	}
}
