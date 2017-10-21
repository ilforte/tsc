/**
 * 
 */

package it.tsc.test.validation;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import it.tsc.domain.PortalUser;
import it.tsc.domain.PortalUser.PortalUserInsert;
import it.tsc.domain.PortalUser.PortalUserRemove;

/**
 * @author astraservice
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class BeanValidationTest extends BaseValidator {
  @Autowired
  private Validator validator;

  /**
   * 
   */
  public BeanValidationTest() {

  }

  @Configuration
  static class Config {

    // this bean will be injected into the OrderServiceTest class
    @Bean
    public LocalValidatorFactoryBean validator() {
      return new LocalValidatorFactoryBean();
    }
  }

  @Test
  public void validatePortalUser() {
    Set<ConstraintViolation<PortalUser>> errors;
    PortalUser portalUser = new PortalUser();
    errors = validator.validate(portalUser);
    printErrors(errors);
    // We are expecting 1 error here.
    // Just the NotNull.
    assertEquals(0, errors.size());
    errors.clear();
    logger.debug("************************");
    portalUser.setEmail("this can not be a valid email");
    errors = validator.validate(portalUser);
    printErrors(errors);
    // We are expecting 1 errors here.
    assertEquals(1, errors.size());
    errors.clear();
  }

  @Test
  public void validatePortalUserWithGroups() {
    Set<ConstraintViolation<PortalUser>> errors;
    PortalUser portalUser = new PortalUser();
    errors = validator.validate(portalUser, PortalUserRemove.class);
    printErrors(errors);
    // We are expecting 1 error here.
    // Just the NotNull.
    assertEquals(2, errors.size());
    errors.clear();
    logger.debug("************************");
    portalUser.setEmail("this can not be a valid email");
    errors = validator.validate(portalUser, PortalUserRemove.class, PortalUserInsert.class);
    printErrors(errors);
    // We are expecting 1 errors here.
    assertEquals(4, errors.size());
    errors.clear();
  }

}
