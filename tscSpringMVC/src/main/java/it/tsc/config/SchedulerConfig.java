/**
 * 
 */
package it.tsc.config;

import java.io.IOException;
import java.util.Properties;

import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import it.tsc.job.AllarmWatcherJob;
import it.tsc.job.AutowiringSpringBeanJobFactory;

/**
 * @author astraservice
 *
 */
@Configuration
@ComponentScan(basePackages = { "it.tsc.job" })
@PropertySource(value = { "classpath:quartz.properties" }, ignoreResourceNotFound = false)
public class SchedulerConfig {
  private static Logger logger = LoggerFactory.getLogger(SchedulerConfig.class);

  /**
   * Class for spring definition scheduler
   */
  public SchedulerConfig() {

  }

  @Autowired
  private ApplicationContext applicationContext;

  @Bean
  public SchedulerFactoryBean quartzScheduler() {
    SchedulerFactoryBean quartzScheduler = new SchedulerFactoryBean();

    quartzScheduler.setQuartzProperties(quartzProperties());
    quartzScheduler.setOverwriteExistingJobs(true);

    // Custom job factory of spring with DI support for @Autowired
    AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
    jobFactory.setApplicationContext(applicationContext);
    quartzScheduler.setJobFactory(jobFactory);

    Trigger[] triggers = { cronTriggerFactoryBean().getObject() };

    quartzScheduler.setTriggers(triggers);

    return quartzScheduler;
  }

  @Bean
  public JobDetailFactoryBean processMyJob() {
    JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
    jobDetailFactory.setJobClass(AllarmWatcherJob.class);
    jobDetailFactory.setDurability(true);
    return jobDetailFactory;
  }

  @Bean
  // Configure cron to fire trigger every 1 minute
  public CronTriggerFactoryBean cronTriggerFactoryBean() {
    CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
    cronTriggerFactoryBean.setJobDetail(processMyJob().getObject());
    cronTriggerFactoryBean.setCronExpression("0/3 * * * * ?");
    return cronTriggerFactoryBean;
  }

  @Bean
  public Properties quartzProperties() {
    PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
    propertiesFactoryBean.setLocation(new ClassPathResource("quartz.properties"));
    Properties properties;

    try {
      propertiesFactoryBean.afterPropertiesSet();
      properties = propertiesFactoryBean.getObject();
    } catch (IOException e) {
      throw new RuntimeException("Unable to load quartz.properties", e);
    }

    return properties;
  }

  // @PreDestroy
  // public void cleanUp() throws Exception {
  // logger.debug("@PreDestroy Spring Container is destroyed");
  // if (this.quartzScheduler() != null) {
  // this.quartzScheduler().setWaitForJobsToCompleteOnShutdown(false);
  // } ;
  // }
  //
  // @PostConstruct
  // public void init() throws Exception {
  // JobDetail job =
  // JobBuilder.newJob(AllarmWatcherJob.class).withIdentity("anyJobName", "group1").build();
  // JobDataMap jobDataMap = job.getJobDataMap();
  // jobDataMap.put("data", "test data");
  // try {
  //
  // this.trigger = TriggerBuilder.newTrigger().withIdentity("anyTriggerName", "group1")
  // .withSchedule(CronScheduleBuilder.cronSchedule("0/3 * * * * ?")).build();
  // SchedulerFactory sf = new StdSchedulerFactory("quartz.properties");
  // this.scheduler = sf.getScheduler();
  // this.scheduler.start();
  // this.scheduler.scheduleJob(job, trigger);
  // logger.debug("@PostConstruct Start job {}", job);
  //
  // } catch (SchedulerException e) {
  // logger.error(e.getMessage());
  // }
  // }
}
