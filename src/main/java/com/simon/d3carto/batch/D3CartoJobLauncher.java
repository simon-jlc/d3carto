package com.simon.d3carto.batch;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.elasticsearch.common.collect.Maps;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 
 * @since 11 avr. 2015
 * @author simon 
 */
@Slf4j
@Component("d3CartoJobLauncher")
public class D3CartoJobLauncher {
	
	@Resource(name="d3CartoJob")
	private Job job;
	
	@Resource(name="jobLauncher")
	private JobLauncher jobLauncher;
	
	@Scheduled(fixedDelayString="${d3carto.batch.schedule.delay}")
	public void launchJob() {
		
		try {
			Map<String, JobParameter> jobParametersMap = Maps.newHashMap();
			jobParametersMap.put("startDate", new JobParameter(new Date().getTime()));
			JobParameters jobParameters = new JobParameters(jobParametersMap);
			jobLauncher.run(job, jobParameters);
			
		} catch (JobExecutionAlreadyRunningException | JobRestartException
				| JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e.getMessage());
		}
	}
}
