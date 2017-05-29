package it.tsc.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@DisallowConcurrentExecution
public class WatcherJob implements Job {
  private static Logger logger = LoggerFactory.getLogger(WatcherJob.class);

  public WatcherJob() {

  }

  @Override
  public void execute(JobExecutionContext executionContext) throws JobExecutionException {
    try {
      Thread.sleep(5L * 1000L);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    logger.debug("execute job: {}", executionContext);
  }

}
