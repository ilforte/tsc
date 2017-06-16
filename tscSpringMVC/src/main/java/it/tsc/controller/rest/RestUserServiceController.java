/**
 * 
 */
package it.tsc.controller.rest;

import java.security.Principal;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;

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
import it.tsc.model.PortalUser.PortalUserInsert;
import it.tsc.model.PortalUser.PortalUserRemove;
import it.tsc.model.PortalUser.PortalUserRenewPassword;
import it.tsc.model.Response;
import it.tsc.model.Role;
import it.tsc.model.ValidationResponse;
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

  @Autowired
  private Validator validator;

  /**
   * get TSC User
   * 
   * @param user
   * @return
   */
  @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
  @RequestMapping(value = "/admin/userService/getUser", method = RequestMethod.GET,
      produces = "application/json")
  public @ResponseBody PortalUser getUser(@AuthenticationPrincipal Principal user) {
    // TODO return rest json service get user
    logger.debug("/admin/userService/getUser");
    return userService.getUser(user.getName());
  }

  /**
   * get All user in JSON format
   * 
   * @param user
   * @return
   */
  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  @RequestMapping(value = "/admin/userService/jsonGetAllUsers", method = RequestMethod.GET,
      produces = "application/json")
  public @ResponseBody String jsonGetAllUsers(@AuthenticationPrincipal Principal user) {
    // TODO return rest json service get user
    logger.debug("/admin/userService/jsonGetAllUsers");
    return userService.jsonGetAllUsers();
  }

  /**
   * Add user rest service errors = validator.validate(portalUser);
   * 
   * @param user
   * @param portalUser
   * @param result
   * @return
   */
  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  @RequestMapping(value = "/admin/userService/jsonAddUser", method = RequestMethod.POST,
      produces = "application/json")
  public @ResponseBody ValidationResponse jsonInsertUser(@AuthenticationPrincipal Principal user,
      @RequestBody PortalUser portalUser, BindingResult result) {
    logger.debug("portalUser: {}", portalUser.getUsername());
    // return rest json service get user
    ValidationResponse res = new ValidationResponse();
    Set<ConstraintViolation<PortalUser>> errors =
        validator.validate(portalUser, PortalUserInsert.class, PortalUserRemove.class);
    if (errors.size() > 0) {
      // if validator failed
      res.setBindingResult(errors);
      logger.debug("portalUser: {} errors: {}", portalUser.getUsername(), result.hasErrors());
      res.setStatus(Response.FAILURE.toString());
    } else {
      if (userService.addUser(portalUser.getUsername(), portalUser.getPassword(),
          portalUser.getEmail(), Role.valueOf(portalUser.getRole())) == true) {
        res.setStatus(Response.SUCCESS.toString());
        logger.debug("no errors");
      } else {
        res.setStatus(Response.FAILURE.toString());
        res.setResultMessage("Error adding user");
      } ;
    }
    return res;
  }

  /**
   * remove User
   * 
   * @param user
   * @param portalUser
   * @param result
   * @return
   */
  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  @RequestMapping(value = "/admin/userService/jsonRemoveUser", method = RequestMethod.POST,
      produces = "application/json")
  public @ResponseBody ValidationResponse jsonRemoveUser(@AuthenticationPrincipal Principal user,
      @Valid @RequestBody PortalUser portalUser, BindingResult result) {
    logger.debug("portalUser: {}", portalUser.getUsername());
    ValidationResponse res = new ValidationResponse();
    Set<ConstraintViolation<PortalUser>> errors =
        validator.validate(portalUser, PortalUserRemove.class);
    // TODO return rest json service get user
    if (errors.size() > 0) {
      // if validator failed
      res.setBindingResult(errors);
      res.setStatus(Response.FAILURE.toString());
      logger.debug("portalUser: {} errors: {}", portalUser.getUsername(), result.hasErrors());
    } else {
      if (userService.removeUser(portalUser.getUsername(),
          Role.valueOf(portalUser.getRole())) == true) {
        res.setStatus(Response.SUCCESS.toString());
        logger.debug("no errors");
      } else {
        res.setStatus(Response.FAILURE.toString());
        res.setResultMessage("Error removing user");
      } ;
    }
    return res;
  }

  /**
   * get User in JSON format
   * 
   * @param user
   * @return
   */
  @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
  @RequestMapping(value = "/user/userService/jsonGetUser", method = RequestMethod.GET,
      produces = "application/json")
  public @ResponseBody String jsonGetUser(@AuthenticationPrincipal Principal user) {
    // TODO return rest json service get user
    logger.debug("/admin/userService/jsonGetUser");
    return userService.jsonGetUser(user.getName());
  }

  @PreAuthorize("permitAll")
  @RequestMapping(value = "/jsonAskNewPassword", method = RequestMethod.POST,
      produces = "application/json")
  public @ResponseBody ValidationResponse jsonAskNewPassword(
      @Valid @RequestBody PortalUser portalUser, BindingResult result) {
    // TODO return rest json service get user
    logger.debug("jsonAskNewPassword");
    ValidationResponse res = new ValidationResponse();
    Set<ConstraintViolation<PortalUser>> errors =
        validator.validate(portalUser, PortalUserRenewPassword.class);
    // TODO return rest json service get user
    if (errors.size() > 0) {
      // if validator failed
      res.setBindingResult(errors);
      res.setStatus(Response.FAILURE.toString());
      logger.debug("portalUser: {} errors: {}", portalUser.getUsername(), result.hasErrors());
    } else {
      PortalUser user = userService.getUser(portalUser.getUsername(), portalUser.getEmail());
      /**
       * always success
       */
      res.setStatus(Response.SUCCESS.toString());
      if (user == null) {
        logger.debug("error requesting jsonAskNewPassword user: {} username: {} emai: {}", user,
            portalUser.getUsername(), portalUser.getEmail());
      } ;
    }
    return res;
  }

  @PreAuthorize("permitAll")
  @RequestMapping(value = "/jsonRenewPassword", method = RequestMethod.POST,
      produces = "application/json")
  public @ResponseBody ValidationResponse jsonRenewPassword(
      @Valid @RequestBody PortalUser portalUser, BindingResult result) {
    // TODO return rest json service get user
    logger.debug("jsonAskNewPassword");
    ValidationResponse res = new ValidationResponse();
    Set<ConstraintViolation<PortalUser>> errors =
        validator.validate(portalUser, PortalUserRenewPassword.class);
    // TODO return rest json service get user
    if (errors.size() > 0) {
      // if validator failed
      res.setBindingResult(errors);
      res.setStatus(Response.FAILURE.toString());
      logger.debug("portalUser: {} errors: {}", portalUser.getUsername(), result.hasErrors());
    } else {
      PortalUser user = userService.getUser(portalUser.getUsername(), portalUser.getEmail());
      /**
       * always success
       */
      res.setStatus(Response.SUCCESS.toString());
      if (user == null) {
        logger.debug("error requesting jsonAskNewPassword user: {} username: {} emai: {}", user,
            portalUser.getUsername(), portalUser.getEmail());
      } ;
    }
    return res;
  }

}
