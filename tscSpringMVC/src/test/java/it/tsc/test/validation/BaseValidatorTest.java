/**
 * 
 */
package it.tsc.test.validation;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.code.tempusfugit.concurrency.ConcurrentTestRunner;

import it.tsc.domain.PortalUser;
import it.tsc.test.parallel.ParallelTest;

/**
 * @author astraservice
 *
 */
public class BaseValidatorTest extends ParallelTest {
  protected static Logger logger = LoggerFactory.getLogger(BaseValidatorTest.class);

  /**
   * 
   */
  public BaseValidatorTest() {
    // TODO Auto-generated constructor stub
  }

  @Override
  @Test
  public void fakeTest() {

  }

  // Utility function to print out errors from validation.
  protected void printErrors(Set<ConstraintViolation<PortalUser>> errors) {
    if (errors.size() > 0) {
      for (ConstraintViolation<PortalUser> error : errors) {
        logger.debug("message: {} path: {}", error.getMessage(), error.getPropertyPath());
      }
    } else {
      logger.debug("There were no errors to print.");
    }
  }

}
