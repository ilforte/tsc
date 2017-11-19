/**
 * 
 */
package it.tsc.test.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import it.tsc.data.config.ServiceConfig;
import it.tsc.web.parallel.WebParallelTest;

/**
 * @author astraservice
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceConfig.class, loader = AnnotationConfigContextLoader.class)
public class BaseDaoTest extends WebParallelTest {

  /**
   * 
   */
  public BaseDaoTest() {

  }

  @Test
  public void baseDaoTest() {

  }

}
