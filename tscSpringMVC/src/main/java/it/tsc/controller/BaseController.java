/**
 * 
 */
package it.tsc.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;

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

  public UserService getUserService() {
    return userService;
  }

  public MessageSource getMessageSource() {
    return messageSource;
  }

}
