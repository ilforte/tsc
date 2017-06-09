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

import it.tsc.model.PortalUser;

/**
 * @author astraservice
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
// ApplicationContext will be loaded from the OrderServiceConfig class
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
    assertEquals(2, errors.size());
    errors.clear();
    portalUser.setEmail("this can not be a valid email");
    errors = validator.validate(portalUser);
    printErrors(errors);
    // We are expecting 1 errors here.
    assertEquals(3, errors.size());
    errors.clear();
  }

}
