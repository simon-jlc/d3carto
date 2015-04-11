package com.simon.d3carto.batch.tasklet;

import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.simon.d3carto.config.D3CartoEnvironmentConfig;

/**
 * 
 * @since 11 avr. 2015
 * @author simon 
 */
@Component("nodeItemReader")
@Scope("step")
@Slf4j
public class NodeItemReader implements Tasklet{

	@Autowired
	private D3CartoEnvironmentConfig d3CartoEnvironmentConfig;
	
	@Override
	public RepeatStatus execute(StepContribution contribution,
			ChunkContext chunkContext) throws Exception {
		log.info("[Tasklet] NodeItemReader.execute() is starting...");
		ExecutionContext ecJob = chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext();
		ecJob.put("environment.app", d3CartoEnvironmentConfig.getMainApplication());
		log.info("[Tasklet] NodeItemReader.execute() is finished");
		return RepeatStatus.FINISHED;
	}
}
