package it.tsc.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
  private static Logger logger = LoggerFactory.getLogger(LoginController.class);

  @RequestMapping(value = {"/", "/welcome**"}, method = RequestMethod.GET)
  public ModelAndView welcomePage() {
    ModelAndView model = new ModelAndView();
    model.addObject("title", "Spring Security Hello World");
    model.addObject("message", "This is welcome page!");
    model.setViewName("hello");
    return model;
  }

  // Spring Security see this :
  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public ModelAndView login(@RequestParam(value = "error", required = false) String error,
      @RequestParam(value = "logout", required = false) String logout) {
    ModelAndView model = new ModelAndView();
    if (error != null) {
      model.addObject("error", "error");
    }

    if (logout != null) {
      model.addObject("msg", "msg");
    }
    logger.debug("invoke login");
    model.setViewName("login");
    return model;
  }


  // for 403 access denied page
  @RequestMapping(value = {"/403", "/admin/userService/403"}, method = RequestMethod.GET)
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
