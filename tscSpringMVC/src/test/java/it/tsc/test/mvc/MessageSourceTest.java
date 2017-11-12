/**
 * 
 */
package it.tsc.test.mvc;

import java.io.IOException;
import java.util.Locale;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import it.tsc.config.WebAppConfig;
import it.tsc.data.config.ServiceConfig;

/**
 * @author astraservice
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextHierarchy({@ContextConfiguration(value = "classpath:spring-security.xml"),
    @ContextConfiguration(classes = WebAppConfig.class),
    @ContextConfiguration(classes = ServiceConfig.class)})
@WebAppConfiguration
public class MessageSourceTest {
  private static Logger logger = LoggerFactory.getLogger(MvcRestUserTest.class);
  @Autowired
  private WebApplicationContext webApplicationContext;

  /**
   * 
   */
  public MessageSourceTest() {
    // TODO Auto-generated constructor stub
  }

  @Test
  public void messageTest() throws IOException {
    String message = webApplicationContext.getMessage("message.accessDenied", null, Locale.ITALY);
    logger.debug("message: {} messageSource: {}", message, webApplicationContext);
    logger.debug("message label.chooseRole: {} messageSource: {}",
        webApplicationContext.getMessage("label.chooseRole", new Object[0], Locale.ITALY),
        webApplicationContext);
  }

}
