/**
 * 
 */
package it.tsc.webservice;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.rpc.ServiceException;
import javax.xml.soap.SOAPException;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import it.tsc.service.UserService;
import it.tsc.util.PortalUtil;

/**
 * @author astraservice
 *
 */
@WebService
public class TscWebService {
  private static Logger logger = LoggerFactory.getLogger(TscWebService.class);
  @Autowired
  private BCryptPasswordEncoder bcryptEncoder;
  @Autowired
  private UserService userService;

  @Resource
  private WebServiceContext wsContext;

  @WebMethod(operationName = "insertAllarmEuropeAssistance")
  public String insertAllarmEuropeAssistance(String tel, String ab_codi, String evento, String user)
      throws SOAPException, ServiceException {
    String username = "";
    String password = "";
    String allarm_uuid = PortalUtil.generateUUID();

    MessageContext mctx = wsContext.getMessageContext();

    // get detail from request headers
    Map http_headers = (Map) mctx.get(MessageContext.HTTP_REQUEST_HEADERS);
    List userList = (List) http_headers.get("Username");
    List passList = (List) http_headers.get("Password");

    if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
      logger.debug("username or password cannot be null");
      throw new SOAPException("username or password cannot be null");
    }

    logger.debug("username: {} password: {}", username, password);

    if (!bcryptEncoder.matches(password, userService.getUser(username).getPassword())) {
      logger.debug("invalid username or password for service access");
      throw new SOAPException("invalid username or password for service access");
    }

    return allarm_uuid;
  }

}
