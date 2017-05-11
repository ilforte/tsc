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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.tsc.model.TscUser;
import it.tsc.service.UserService;

/**
 * @author astraservice
 *
 */

@RestController
public class RestUserServiceController {
  private static Logger logger = LoggerFactory.getLogger(RestUserServiceController.class);

  @Autowired
  private UserService userService;

  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  @RequestMapping(value = "/admin/userService/getUsers", method = RequestMethod.GET,
      produces = "application/json")
  public @ResponseBody TscUser getUsers(@AuthenticationPrincipal Principal user) {
    // TODO return rest json service get user
    logger.debug("/admin/userService/getUsers");
    return userService.getUser(user.getName());
  }

}
