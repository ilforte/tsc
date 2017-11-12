/**
 * 
 */
package it.tsc.test.ws;

import static org.junit.Assert.assertTrue;

import javax.xml.ws.Endpoint;
import javax.xml.ws.WebServiceException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import it.tsc.config.WebAppConfig;
import it.tsc.data.config.ServiceConfig;
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
  private static final String WS_URL =
      "http://localhost:8080/tscSpringMVC/services/TscWebService?wsdl";

  /**
   * 
   */
  public WSDialogTest() {
    // TODO Auto-generated constructor stub
  }

  @Test(expected = WebServiceException.class)
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

  // @Test(expected = Exception.class)
  // public void testSoapHeaders() throws SOAPException, ServiceException, MalformedURLException {
  // URL url = new URL(WS_URL);
  // QName qname = new QName("http://webservice.tsc.it/", "TscWebService");
  //
  // Service service = Service.create(url, qname);
  // TscWebService tscWebService = service.getPort(TscWebService.class);
  //
  // /******************* UserName & Password ******************************/
  // Map<String, Object> req_ctx = ((BindingProvider) tscWebService).getRequestContext();
  // req_ctx.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, WS_URL);
  //
  // Map<String, List<String>> headers = new HashMap<String, List<String>>();
  // headers.put("Username", Collections.singletonList("mkyong"));
  // headers.put("Password", Collections.singletonList("password"));
  // req_ctx.put(MessageContext.HTTP_REQUEST_HEADERS, headers);
  // /**********************************************************************/
  //
  // System.out.println(tscWebService.insertAllarmEuropeAssistance(null, null, null, null));
  // }

}
