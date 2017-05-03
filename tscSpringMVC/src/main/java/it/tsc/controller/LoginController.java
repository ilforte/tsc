package it.tsc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);
    
    @RequestMapping(value = "/login", method = RequestMethod.HEAD)
    public void login(){

        logger.trace("trace logging");
        logger.debug("debug logging");
        logger.info("info logging");
        logger.warn("warning logging");
        logger.error("error logging", new RuntimeException("help"));

    }
    
    @RequestMapping(value = "/logout", method = RequestMethod.HEAD)
    public void logout(){

        logger.trace("trace logging");
        logger.debug("debug logging");
        logger.info("info logging");
        logger.warn("warning logging");
        logger.error("error logging", new RuntimeException("help"));

    }
    
}
