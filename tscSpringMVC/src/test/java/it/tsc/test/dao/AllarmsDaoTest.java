/**
 * 
 */
package it.tsc.test.dao;

import java.text.ParseException;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import it.tsc.config.ServiceConfig;
import it.tsc.service.AllarmService;
import it.tsc.util.PortalUtil;

/**
 * @author astraservice
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceConfig.class, loader = AnnotationConfigContextLoader.class)
public class AllarmsDaoTest {
  private static Logger logger = LoggerFactory.getLogger(AllarmsDaoTest.class);
  @Autowired
  private AllarmService allarmService;

  /**
   * 
   */
  public AllarmsDaoTest() {
    // TODO Auto-generated constructor stub
  }

  @Test
  public void testInsertAllarmeMatricola() throws ParseException {
    allarmService.insertAllarmeMatricola("064795", "000000", new Date(), "1",
        PortalUtil.generateUUID(), "matteo");
  }

  @Test
  public void testInsertAllarmeMatricolaSound() throws ParseException {
    allarmService.insertAllarmeMatricola("064795", "000000", new Date(), "1",
        PortalUtil.generateUUID(), "");
  }

  @Test
  public void removeAllarmDao() throws ParseException {
    allarmService.removeAllarme("1234");
  }

  @Test
  public void removeAllarmSound() throws ParseException {
    allarmService.removeAllarme("1235");
  }

  @Test
  public void getJsonAllarms() throws ParseException {
    logger.debug(allarmService.jsonGetAllarms());
  }

}
