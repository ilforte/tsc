package it.tsc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class HomeController {

  private final Logger logger = LoggerFactory.getLogger(HomeController.class);

  public HomeController() {

  }
}
