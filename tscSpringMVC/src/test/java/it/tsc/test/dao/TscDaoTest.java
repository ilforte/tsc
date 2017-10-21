/**
 * 
 */
package it.tsc.test.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import it.tsc.domain.Allarm;
import it.tsc.service.TscService;

/**
 * @author astraservice
 *
 */
public class TscDaoTest extends BaseDaoTest {
  private static Logger logger = LoggerFactory.getLogger(TscDaoTest.class);
  @Autowired
  private TscService tscService;

  /**
   * 
   */
  public TscDaoTest() {
    logger.debug("TscDaoTest");
  }

  @Test
  public void test() {
    assertTrue(tscService != null);
  }

  @Test
  public void testGetAnagrafica() {
    Allarm allarm = new Allarm();
    allarm.setAb_codi("000000");
    assertNotNull(tscService.getAnagrafica(allarm));
  }

}
