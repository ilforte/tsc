/**
 * 
 */
package it.tsc.test.validation;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.tsc.model.PortalUser;

/**
 * @author astraservice
 *
 */
public class BaseValidator {
  protected static Logger logger = LoggerFactory.getLogger(BaseValidator.class);

  /**
   * 
   */
  public BaseValidator() {
    // TODO Auto-generated constructor stub
  }

  // Utility function to print out errors from validation.
  protected void printErrors(Set<ConstraintViolation<PortalUser>> errors) {
    if (errors.size() > 0) {
      for (ConstraintViolation<PortalUser> error : errors) {
        logger.debug(error.getMessage());
      }
    } else {
      logger.debug("There were no errors to print.");
    }
  }

}
