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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.tsc.model.Allarm;
import it.tsc.service.AllarmService;

/**
 * @author astraservice
 *
 */

@RestController
public class RestAllarmServiceController {
  private static Logger logger = LoggerFactory.getLogger(RestAllarmServiceController.class);

  @Autowired
  private AllarmService allarmService;

  /**
   * remove allarm from queue
   * 
   * @param allarm
   * @param user
   * @return
   */
  @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_SADMIN') or hasAuthority('ROLE_USER')")
  @RequestMapping(value = "/user/allarmService/removeAllarm", method = RequestMethod.POST,
      produces = "application/json")
  public @ResponseBody Allarm removeAllarm(@RequestBody Allarm allarm,
      @AuthenticationPrincipal Principal user) {
    logger.debug("getSerial_uuid() {} user: {}", allarm.getSerial_uuid());
    logger.debug("user: {}", user.getName());
    allarmService.removeAllarme(allarm.getSerial_uuid());
    return allarm;
  }

  /**
   * update user in Allarm open
   * 
   * @param allarm
   * @param user
   * @return
   */
  @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_SADMIN') or hasAuthority('ROLE_USER')")
  @RequestMapping(value = "/user/allarmService/updateAllarm", method = RequestMethod.POST,
      produces = "application/json")
  public @ResponseBody Allarm updateAllarm(@RequestBody Allarm allarm,
      @AuthenticationPrincipal Principal user) {
    logger.debug("getSerial_uuid() {} user: {}", allarm.getSerial_uuid(), user.getName());
    allarmService.updateAllarme(allarm.getSerial_uuid(), user.getName());
    return allarm;
  }

}
