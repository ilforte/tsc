/**
 * 
 */
package it.tsc.test.ws;

import static org.junit.Assert.assertTrue;

import javax.xml.ws.Endpoint;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import it.tsc.config.ServiceConfig;
import it.tsc.config.WebAppConfig;
import it.tsc.webservice.TscWebService;

/**
 * @author astraservice
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextHierarchy({ @ContextConfiguration(value = "classpath:spring-security.xml"),
    @ContextConfiguration(classes = WebAppConfig.class),
    @ContextConfiguration(classes = ServiceConfig.class) })
@WebAppConfiguration
public class WSDialogTest {
  private static Logger logger = LoggerFactory.getLogger(WSDialogTest.class);
  public static final String URI = "http://localhost:8080/tscSpringMVC/services/TscWebService";

  /**
   * 
   */
  public WSDialogTest() {
    // TODO Auto-generated constructor stub
  }

  @Test
  public void testWS() {
    // Create instance of service implementation
    TscWebService impl = new TscWebService();
    // Make available
    Endpoint endpoint = Endpoint.publish(URI, impl);

    // Test that it is available
    boolean status = endpoint.isPublished();
    System.out.println("Web service status = " + status);
    assertTrue(status);
  }

}
