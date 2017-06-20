/**
 * 
 */
package it.tsc.controller.rest;

import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author astraservice
 *
 */
@RestController
public class RestBaseController {

  @Autowired
  protected Validator validator;

  /**
   * Base Rest Class
   */
  public RestBaseController() {

  }

}
