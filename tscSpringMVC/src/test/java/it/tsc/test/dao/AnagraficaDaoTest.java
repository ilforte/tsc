/**
 * 
 */
package it.tsc.test.dao;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import it.tsc.service.GroupService;

/**
 * @author astraservice
 *
 */
public class AnagraficaDaoTest extends BaseDaoTest {
  private static Logger logger = LoggerFactory.getLogger(AnagraficaDaoTest.class);
  @Autowired
  private GroupService groupService;

  /**
   * 
   */
  public AnagraficaDaoTest() {

  }

  @Test
  public void Test() {
    assertTrue(groupService != null);
  }

}
