package it.tsc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class AppController {

  @RequestMapping(value = {"/home"}, method = RequestMethod.GET)
  public String homePage(ModelMap model) {
    return "home";
  }

  @RequestMapping(value = {"/admin"}, method = RequestMethod.GET)
  public String productsPage(ModelMap model) {
    return "admin";
  }

  @RequestMapping(value = {"/contactus"}, method = RequestMethod.GET)
  public String contactUsPage(ModelMap model) {
    return "contactus";
  }
}
