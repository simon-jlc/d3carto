package com.simon.d3carto.batch;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

/**
 * 
 * @since 11 avr. 2015
 * @author simon 
 */
@Slf4j
@Component("d3CartoJobLauncher")
public class D3CartoJobLauncher {
	
//	@Resource(name="d3CartoJob")
//	private Job job;
//	
//	@Resource(name="jobLauncher")
//	private JobLauncher jobLauncher;
//	
//	@Scheduled(fixedDelay=5000)
//	public void launchJob() {
//		log.info("Job d3CartoJob is starting...");
//		log.info("Job {} is starting...", job.getName());
//		
//		try {
//			jobLauncher.run(job, null);
//			log.info("Job {} has been executed", job.getName());
//		} catch (JobExecutionAlreadyRunningException | JobRestartException
//				| JobInstanceAlreadyCompleteException
//				| JobParametersInvalidException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			log.error(e.getMessage());
//		}
//	}
}
