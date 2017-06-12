package it.tsc.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import it.tsc.model.Role;

@Controller
@RequestMapping("/")
public class AppController {

  @RequestMapping(value = {"/home"}, method = RequestMethod.GET)
  public ModelAndView homePage() {
    ModelAndView model = new ModelAndView();
    model.addObject("title", "Spring Security Hello World");
    model.addObject("message", "This is welcome page!");
    model.setViewName("home");
    return model;
  }

  @RequestMapping(value = {"/admin"}, method = RequestMethod.GET)
  public ModelAndView adminPage() {
    ModelAndView model = new ModelAndView();
    model.addObject("roles", roles());
    model.setViewName("admin");
    return model;
  }

  @RequestMapping(value = {"/user"}, method = RequestMethod.GET)
  public ModelAndView userPage() {
    ModelAndView model = new ModelAndView();
    model.addObject("roles", roles());
    model.setViewName("user");
    return model;
  }

  protected Map<String, String> roles() {
    Map<String, String> roles = new HashMap<String, String>();
    roles.put(Role.ROLE_ADMIN.toString(), "Admin");
    roles.put(Role.ROLE_USER.toString(), "User");
    roles.put(Role.ROLE_BACKOFFICE.toString(), "Back Office");
    return roles;
  }

}
