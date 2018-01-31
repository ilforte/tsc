/**
 * 
 */
package it.tsc.webservice;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.rpc.ServiceException;
import javax.xml.soap.SOAPException;
import javax.xml.ws.WebServiceContext;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import it.tsc.data.config.ServiceConfig;
import it.tsc.service.AllarmService;
import it.tsc.service.UserService;
import it.tsc.service.impl.AllarmServiceImpl;
import it.tsc.service.impl.UserServiceImpl;
import it.tsc.util.PortalUtil;
import it.tsc.util.TimeUtil;

/**
 * @author astraservice
 *
 */
@WebService
public class TscWebService {
  private static Logger logger = LoggerFactory.getLogger(TscWebService.class);
  private BCryptPasswordEncoder bcryptEncoder;
  private UserService userService;
  private AllarmService allarmService;
  private ApplicationContext context = new AnnotationConfigApplicationContext(ServiceConfig.class);

  @Resource
  private WebServiceContext wsContext;

  @WebMethod(operationName = "insertAllarmEuropeAssistance")
  public String insertAllarmEuropeAssistance(String tel, String ab_codi, String evento, String user,
      String password) throws SOAPException, ServiceException {

    String allarm_uuid = PortalUtil.generateUUID();
    Validate.notNull(context, "context cannot be null");
    allarmService = context.getBean("allarmService", AllarmServiceImpl.class);
    Validate.notNull(allarmService, "allarmService cannot be null");
    userService = context.getBean("userService", UserServiceImpl.class);
    Validate.notNull(userService, "userService cannot be null");
    bcryptEncoder = context.getBean("bcryptEncoder", BCryptPasswordEncoder.class);
    Validate.notNull(bcryptEncoder, "bcryptEncoder cannot be null");

    if (StringUtils.isEmpty(user) || StringUtils.isEmpty(password)) {
      logger.debug("username or password cannot be null");
      throw new SOAPException("username or password cannot be null");
    }

    logger.debug("username: {}", user);
    // !bcryptEncoder.matches(password, userService.getUser(user).getPassword())
    if (userService.getUser(user) == null) {
      logger.debug("invalid username for service access");
      throw new SOAPException("invalid username or password for service access");
    } else {
      logger.debug("sussess inserting allarm");
      allarmService.insertAllarmeMatricola("EUROPE_ASSISTANCE", ab_codi,
          TimeUtil.getCurrentInstantDate(), evento, PortalUtil.generateUUID(), "");
    }
    return allarm_uuid;
  }

}
