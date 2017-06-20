package it.tsc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class AppController extends BaseController {

  @RequestMapping(value = {"/home"}, method = RequestMethod.GET)
  public ModelAndView homePage(@RequestParam(value = "ab_codi", required = false) String ab_codi,
      HttpServletRequest request) {
    ModelAndView model = new ModelAndView();
    model.addObject("ab_codi", ab_codi);
    model.setViewName("home");
    return model;
  }

  @RequestMapping(value = {"/admin"}, method = RequestMethod.GET)
  public ModelAndView adminPage(@RequestParam(value = "ab_codi", required = false) String ab_codi,
      HttpServletRequest request) {
    ModelAndView model = new ModelAndView();
    request.getSession().setAttribute("ab_codi", ab_codi);
    model.addObject("ab_codi", ab_codi);
    model.addObject("roles", roles());
    model.setViewName("admin");
    return model;
  }

  @RequestMapping(value = {"/user"}, method = RequestMethod.GET)
  public ModelAndView userPage(@RequestParam(value = "ab_codi", required = false) String ab_codi,
      HttpServletRequest request) {
    ModelAndView model = new ModelAndView();
    request.getSession().setAttribute("ab_codi", ab_codi);
    model.addObject("ab_codi", ab_codi);
    model.addObject("roles", roles());
    model.setViewName("user");
    return model;
  }

}
