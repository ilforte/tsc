package it.tsc.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import it.tsc.model.PortalUser;

@Controller
public class LoginController extends BaseController {
  private static Logger logger = LoggerFactory.getLogger(LoginController.class);



  @RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
  public ModelAndView welcomePage() {
    ModelAndView model = new ModelAndView();
    model.addObject("title", "Welcome to TSC site");
    model.addObject("message", "Welcome to TSC site");
    model.setViewName("welcome");
    return model;
  }

  /**
   * ask new password through email
   * 
   * @return
   */
  @RequestMapping(value = { "/askNewPassword" }, method = RequestMethod.GET)
  public ModelAndView askNewPassword() {
    ModelAndView model = new ModelAndView();
    model.setViewName("asknew");
    return model;
  }

  @RequestMapping(value = { "/renewPassword" }, method = RequestMethod.GET)
  public ModelAndView renewPassword(@RequestParam("username") String username,
      @RequestParam("tmpPassword") String tmpPassword) {
    ModelAndView model = new ModelAndView();
    model.addObject("username", username);
    /**
     * retrieve user data
     */
    PortalUser user = getUserService().getUser(username);
    if (user != null) {
      model.addObject("email", user.getEmail());
    } else {

    }
    model.addObject("tmpPassword", tmpPassword);
    /**
     * check if password can be requested using service
     */
    model.setViewName("renew");
    return model;
  }

  // Spring Security see this :
  @RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
  public ModelAndView login(@RequestParam(value = "error", required = false) String error,
      @RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {
    ModelAndView model = new ModelAndView();
    if (error != null) {
      model.addObject("error", "error");
    }

    if (logout != null) {
      model.addObject("msg", "msg");
    }
    if (logout != null && request.getSession() != null) {
      logger.debug("invalidate session");
      request.getSession().invalidate();
    }
    logger.debug("invoke login");
    model.setViewName("login");
    return model;
  }

  @RequestMapping(value = "/logout", method = { RequestMethod.GET, RequestMethod.POST })
  public ModelAndView logout(@RequestParam(value = "error", required = false) String error,
      @RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {
    ModelAndView model = new ModelAndView();
    logger.debug("invalidate session");
    request.getSession().invalidate();
    logger.debug("invoke logout");
    model.setViewName("login");
    return model;
  }


  // for 403 access denied page
  @RequestMapping(value = { "/403", "/admin/userService/403" }, method = RequestMethod.GET)
  public ModelAndView accesssDenied(Principal user) {
    ModelAndView model = new ModelAndView();

    if (user != null) {
      model.addObject("msg",
          "Hi " + user.getName() + ", you do not have permission to access this page!");
    } else {
      model.addObject("msg", "You do not have permission to access this page!");
    }
    logger.debug("accesssDenied for: {}", user);
    model.setViewName("/errors/403");
    return model;
  }

}
