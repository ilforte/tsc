/**
 * 
 */
package it.tsc.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;

import it.tsc.model.Role;
import it.tsc.service.UserService;

/**
 * @author astraservice Base class for all controller
 */
@Controller
public class BaseController {
  @Autowired
  private MessageSource messageSource;
  private Locale defaultLocale = new Locale("it", "IT");

  @Autowired
  private UserService userService;

  /**
   * 
   */
  public BaseController() {
    // TODO Auto-generated constructor stub
  }

  public String getMessage(String key) {
    return messageSource.getMessage(key, null, defaultLocale);
  }

  public MessageSource getMessageSource() {
    return messageSource;
  }

  protected UserService getUserService() {
    return userService;
  }

  /**
   * Role Map
   * 
   * @return
   */
  protected Map<String, String> roles() {
    Map<String, String> roles = new HashMap<String, String>();
    roles.put("", "");
    roles.put(Role.ROLE_ADMIN.toString(), "Admin");
    roles.put(Role.ROLE_USER.toString(), "User");
    roles.put(Role.ROLE_BACKOFFICE.toString(), "Back Office");
    return roles;
  }

  /**
   * check if authenticated
   * 
   * @param request
   * @return
   */
  protected boolean isMfaAuthenticated(HttpServletRequest request) {
    if (request.getSession().getAttribute("isMfaAuthenticated") == null) {
      return false;
    } else {
      if (request.getSession().getAttribute("isMfaAuthenticated") instanceof String) {
        if (((String) request.getSession().getAttribute("isMfaAuthenticated")).equals("true")) {
          return true;
        } else {
          return false;
        }
      } else {
        return false;
      }
    }
  }

  /**
   * set Authenticated
   * 
   * @param request
   * @param value
   */
  protected void setMfaAuthenticated(HttpServletRequest request, String value) {
    request.getSession().setAttribute("isMfaAuthenticated", value);
  }

}
