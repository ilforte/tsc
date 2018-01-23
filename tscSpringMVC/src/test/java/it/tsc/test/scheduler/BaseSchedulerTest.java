/**
 * 
 */
package it.tsc.test.scheduler;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import it.tsc.data.config.SchedulerConfig;
import it.tsc.data.config.ServiceConfig;
import it.tsc.job.AllarmServiceJob;
import it.tsc.web.parallel.WebParallelTest;

/**
 * @author astraservice
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ServiceConfig.class, SchedulerConfig.class },
    loader = AnnotationConfigContextLoader.class)
public class BaseSchedulerTest extends WebParallelTest {
  private static Logger logger = LoggerFactory.getLogger(BaseSchedulerTest.class);

  @Autowired
  private CronTriggerFactoryBean cronTriggerFactoryBean;

  @Autowired
  private AllarmServiceJob allarmServiceJob;

  /**
   * 
   */
  public BaseSchedulerTest() {

  }

  @Test
  public void baseSchedulerTest() {
    int i = 1;
    int timer = 12000;
    if (i == 1) {
      try {
        logger.debug("waiting for timer {}", timer);
        Thread.sleep(timer); // 1 second
      } catch (InterruptedException ex) {
        // handle error
      }
    }
  }

  @Test
  public void testScheduler() {
    Assert.assertTrue(cronTriggerFactoryBean != null);
  }

  @Test
  public void testJobScheduler() {
    Assert.assertTrue(allarmServiceJob != null);
  }

}
