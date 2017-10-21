/**
 * 
 */
package it.tsc.controller.rest;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.tsc.domain.Allarm;
import it.tsc.service.TscService;

/**
 * @author astraservice
 *
 */
@RestController
public class RestTscServiceController extends RestBaseController {
  private static Logger logger = LoggerFactory.getLogger(RestTscServiceController.class);
  @Autowired
  private TscService tscService;

  /**
   * 
   */
  public RestTscServiceController() {

  }

  /**
   * get TSC User
   * 
   * @param user
   * @return
   */
  @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_SADMIN') or hasAuthority('ROLE_USER')")
  @RequestMapping(value = "/user/tscService/getAnagrafica", method = RequestMethod.POST,
      produces = "application/json")
  public @ResponseBody String getAnagrafica(@AuthenticationPrincipal Principal user,
      @RequestBody Allarm allarm, BindingResult result) {
    // TODO return rest json service get user
    logger.debug("/user/tscService/getAnagrafica");
    return tscService.getAnagrafica(allarm);
  }

}
