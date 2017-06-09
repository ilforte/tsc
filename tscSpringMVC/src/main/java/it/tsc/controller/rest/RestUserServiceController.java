/**
 * 
 */
package it.tsc.controller.rest;

import java.security.Principal;

import javax.validation.Valid;

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

import it.tsc.model.PortalUser;
import it.tsc.model.Role;
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

  @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
  @RequestMapping(value = "/admin/userService/getUsers", method = RequestMethod.GET,
      produces = "application/json")
  public @ResponseBody PortalUser getUsers(@AuthenticationPrincipal Principal user) {
    // TODO return rest json service get user
    logger.debug("/admin/userService/getUsers");
    return userService.getUser(user.getName());
  }

  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  @RequestMapping(value = "/admin/userService/jsonGetAllUsers", method = RequestMethod.GET,
      produces = "application/json")
  public @ResponseBody String jsonGetAllUsers(@AuthenticationPrincipal Principal user) {
    // TODO return rest json service get user
    logger.debug("/admin/userService/jsonGetAllUsers");
    return userService.jsonGetAllUsers();
  }

  /**
   * Add user rest service
   * 
   * @param user
   * @param portalUser
   * @param result
   * @return
   */
  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  @RequestMapping(value = "/admin/userService/jsonAddUser", method = RequestMethod.POST,
      produces = "application/json")
  public @ResponseBody String jsonInsertUser(@AuthenticationPrincipal Principal user,
      @Valid @RequestBody PortalUser portalUser, BindingResult result) {
    logger.debug("portalUser: {}", portalUser.getUsername());
    // TODO return rest json service get user
    if (result.hasErrors()) {
      // if validator failed
      logger.debug("portalUser: {} errors: {}", portalUser.getUsername(), result.hasErrors());
    } else {
      userService.addUser(portalUser.getUsername(), portalUser.getPassword(), portalUser.getEmail(),
          Role.valueOf(portalUser.getRole()));
      logger.debug("no errors");
    }
    return null;
  }

  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  @RequestMapping(value = "/admin/userService/jsonRemoveUser", method = RequestMethod.POST,
      produces = "application/json")
  public @ResponseBody String jsonRemoveUser(@AuthenticationPrincipal Principal user,
      @Valid @RequestBody PortalUser portalUser, BindingResult result) {
    logger.debug("portalUser: {}", portalUser.getUsername());
    // TODO return rest json service get user
    if (result.hasErrors()) {
      // if validator failed
      logger.debug("portalUser: {} errors: {}", portalUser.getUsername(), result.hasErrors());
    } else {
      userService.removeUser(portalUser.getUsername());
      logger.debug("no errors");
    }
    return null;
  }

  @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
  @RequestMapping(value = "/user/userService/jsonGetUser", method = RequestMethod.GET,
      produces = "application/json")
  public @ResponseBody String jsonGetUser(@AuthenticationPrincipal Principal user) {
    // TODO return rest json service get user
    logger.debug("/admin/userService/jsonGetUser");
    return userService.jsonGetUser(user.getName());
  }

}
