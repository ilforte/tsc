/**
 * 
 */
package it.tsc.test.dao;

import java.text.ParseException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import it.tsc.service.AllarmService;
import it.tsc.util.PortalUtil;
import it.tsc.util.TimeUtil;

/**
 * @author astraservice
 *
 */
public class AllarmsDaoTest extends BaseDaoTest {
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
    allarmService.insertAllarmeMatricola("064795", "000000", null, "1", PortalUtil.generateUUID(),
        "matteo");
  }

  @Test
  public void testInsertAllarmeMatricolaSound() throws ParseException {
    allarmService.insertAllarmeMatricola("064795", "N00001", TimeUtil.getCurrentInstantDate(), "1",
        PortalUtil.generateUUID(), "");
  }

  @Test
  public void removeAllarmDao() throws ParseException {
    allarmService.removeAllarme("2017102112:00:44.223_699a0958");
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
