/**
 * 
 */
package it.tsc.data.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PreDestroy;

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

import it.tsc.job.AllarmServiceJob;
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
	public JobDetailFactoryBean jobDetailFactoryBean() {
		JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
		jobDetailFactory.setJobClass(AllarmServiceJob.class);
		jobDetailFactory.setDurability(true);
		return jobDetailFactory;
	}

	@Bean
	/**
	 * this bean contains global data map
	 * 
	 * @return
	 */
	public Map<String, Object> jobDataMap() {
		Map map = new HashMap<>();
		return map;
	}

	@Bean
	// Configure cron to fire trigger every 1 minute
	public CronTriggerFactoryBean cronTriggerFactoryBean() {
		CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
		cronTriggerFactoryBean.setJobDetail(jobDetailFactoryBean().getObject());
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

	@PreDestroy
	public void cleanUp() throws Exception {
		logger.debug("@PreDestroy Spring Container is destroyed");
	}

	// @PostConstruct

}
