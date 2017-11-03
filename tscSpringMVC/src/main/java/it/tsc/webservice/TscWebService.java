/**
 * 
 */
package it.tsc.webservice;

import java.util.Map;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.soap.SOAPException;
import javax.xml.ws.WebServiceContext;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import it.tsc.service.UserService;

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
  private WebServiceContext ctx;

  @WebMethod(operationName = "insertAllarmEuropeAssistance")
  public boolean insertAllarmEuropeAssistance(String tel, String ab_codi, String evento,
      String user) throws SOAPException {
    String username = (String) ctx.getMessageContext().get("Username");
    String password = (String) ctx.getMessageContext().get("Password");

    Map<String, Object> map = ctx.getMessageContext();
    for (Object obj : map.entrySet()) {
      System.out.println(obj);
    }

    if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
      logger.debug("username or password cannot be null");
      throw new SOAPException("username or password cannot be null");
    }

    if (!bcryptEncoder.matches(password, userService.getUser(username).getPassword())) {
      logger.debug("invalid username or password for service access");
      throw new SOAPException("invalid username or password for service access");
    }

    return false;
  }
}
