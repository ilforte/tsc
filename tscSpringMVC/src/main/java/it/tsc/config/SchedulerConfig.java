/**
 * 
 */
package it.tsc.config;

import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

/**
 * @author astraservice
 *
 */
@Configuration
public class SchedulerConfig implements InitializingBean {
  private static Logger logger = LoggerFactory.getLogger(SchedulerConfig.class);
  // private Trigger trigger;
  // private Scheduler scheduler;

  /**
   * Class for spring definition scheduler
   */
  public SchedulerConfig() {

  }

  @PreDestroy
  public void cleanUp() throws Exception {
    logger.debug("SchedulerConfig Spring Container is destroy");
    // if (this.scheduler != null) {
    // this.scheduler.shutdown(true);
    // } ;
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    // JobDetail job =
    // JobBuilder.newJob(WatcherJob.class).withIdentity("anyJobName", "group1").build();
    // try {
    //
    // this.trigger = TriggerBuilder.newTrigger().withIdentity("anyTriggerName", "group1")
    // .withSchedule(CronScheduleBuilder.cronSchedule("0/3 * * * * ?")).build();
    // SchedulerFactory sf = new StdSchedulerFactory("quartz.properties");
    // this.scheduler = sf.getScheduler();
    // this.scheduler.start();
    // this.scheduler.scheduleJob(job, trigger);
    // logger.debug("Start job ", job);
    //
    // } catch (SchedulerException e) {
    // logger.error(e.getMessage());
    // }
  }
}
