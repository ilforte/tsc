/**
 * 
 */
package it.tsc.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.tsc.service.AllarmService;

/**
 * @author astraservice Job for allarm watcher
 */
@Service
@DisallowConcurrentExecution
public class AllarmWatcherJob implements Job {
  private static Logger logger = LoggerFactory.getLogger(AllarmWatcherJob.class);
  @Autowired
  private AllarmService allarmService;

  @Override
  public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
    logger.debug("Start job");
    if (allarmService != null) {
      logger.debug("AllarmWatcherJob result: {}", allarmService.jsonGetAllarms());
    }
    logger.debug("End job");
  }

}
