/**
 * 
 */
package tscSpringMVC;

import java.text.ParseException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.google.gson.Gson;

import it.tsc.config.ServiceConfig;
import it.tsc.service.AllarmService;
import it.tsc.util.TimeUtil;

/**
 * @author astraservice
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
// ApplicationContext will be loaded from the OrderServiceConfig class
@ContextConfiguration(classes = ServiceConfig.class, loader = AnnotationConfigContextLoader.class)
public class AllarmsDaoTest {
  private static Logger logger = LoggerFactory.getLogger(AllarmsDaoTest.class);
  private Gson gson = new Gson();
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
    allarmService.insertAllarmeMatricola("064795", "000000", TimeUtil.getCurrentTimeStamp(), "1",
        "1234", "matteo");
  }

  @Test
  public void testAllarmDao2() throws ParseException {
    allarmService.insertAllarmeMatricola("064795", "000000", TimeUtil.getCurrentTimeStamp(), "1",
        "1235", "");
  }

  @Test
  public void removeAllarmDao() throws ParseException {
    allarmService.removeAllarme("1234");
  }

  @Test
  public void removeAllarmPlaying() throws ParseException {
    allarmService.removeAllarme("1235");
  }

  @Test
  public void getJsonAllarms() throws ParseException {
    logger.debug(allarmService.jsonGetAllarms());
  }

  @Test
  public void testGetTimestamp() {
    logger.info("testGetTimestamp {}", TimeUtil.getCurrentTimeStamp());
  }

}
