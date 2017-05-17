/**
 * 
 */
package tscSpringMVC;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.google.gson.Gson;

import it.tsc.config.ServiceConfig;
import it.tsc.model.Role;
import it.tsc.model.TscUser;
import it.tsc.service.UserService;

/**
 * @author astraservice
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
// ApplicationContext will be loaded from the OrderServiceConfig class
@ContextConfiguration(classes = ServiceConfig.class, loader = AnnotationConfigContextLoader.class)
public class UserDaoTest {
  private static Logger logger = LoggerFactory.getLogger(UserDaoTest.class);
  @Autowired
  private UserService userService;
  private Gson gson = new Gson();

  /**
   * 
   */
  public UserDaoTest() {

  }

  @Test
  public void testUserDao() {
    logger.info("userService user {}", gson.toJson(userService.getUser("matteo")));
  }

  @Test
  public void testAllUsersDao() {
    logger.info("userService user {}", gson.toJson(userService.getAllUsers()));
  }

  @Test
  public void testJsonAllUsersDao() {
    logger.info("userService user {}", userService.jsonGetAllUsers());
  }

  @Test
  public void testAdmin() {
    TscUser user = new TscUser();
    user.setUsername("ivan");
    logger.info("test user {} Admin role: {}", user.getUsername(), userService.isAdmin(user));
  }

  @Test
  public void testAdmin2() {
    TscUser user = new TscUser();
    user.setUsername("matteo");
    logger.info("test user {} Admin role: {}", user.getUsername(), userService.isAdmin(user));
  }

  @Test
  public void testAddUser() {
    userService.addUser("testUser", "testUser", "testUser@tsc.it", Role.ROLE_USER);
  }

  @Test
  public void testRemoveUser() {
    userService.removeUser("testUser");
  }

  @Test
  public void testUpdateUser() {
    userService.updateUser("testUser", "testUser1", "testUser@tsc.it", Role.ROLE_USER);
  }

  @Test
  public void testUpdateUserFailed() {
    userService.updateUser("testUser", "testUser2", "testUser@tsc.it", Role.ROLE_ADMIN);
  }


}
