/**
 * 
 */
package it.tsc.test.validation;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import it.tsc.config.ServiceConfig;
import it.tsc.model.PortalUser;
import it.tsc.model.PortalUser.PortalUserInsert;
import it.tsc.model.PortalUser.PortalUserRemove;

/**
 * @author astraservice
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceConfig.class, loader = AnnotationConfigContextLoader.class)
public class ValidationTest extends BaseValidator {
  private static Validator validator;

  /**
   * 
   */
  public ValidationTest() {
    // TODO Auto-generated constructor stub
  }

  @BeforeClass
  public static void init() {
    validator = Validation.buildDefaultValidatorFactory().getValidator();
  }

  @Test
  public void validatePortalUser() {
    Set<ConstraintViolation<PortalUser>> errors;
    PortalUser portalUser = new PortalUser();
    errors = validator.validate(portalUser, PortalUserRemove.class);
    printErrors(errors);
    // We are expecting 1 error here.
    // Just the NotNull.
    assertEquals(2, errors.size());
    errors.clear();
    portalUser.setEmail("this can not be a valid email");
    errors = validator.validate(portalUser, PortalUserRemove.class, PortalUserInsert.class);
    printErrors(errors);
    // We are expecting 1 errors here.
    assertEquals(4, errors.size());
    errors.clear();
  }

}
