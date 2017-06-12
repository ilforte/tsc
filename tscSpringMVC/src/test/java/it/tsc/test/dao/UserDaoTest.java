/**
 * 
 */
package it.tsc.test.dao;

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
import it.tsc.model.PortalUser;
import it.tsc.model.Role;
import it.tsc.service.UserService;

/**
 * @author astraservice
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
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
    PortalUser user = new PortalUser();
    user.setUsername("ivan");
    logger.info("test user {} Admin role: {}", user.getUsername(), userService.isAdmin(user));
  }

  @Test
  public void testAdmin2() {
    PortalUser user = new PortalUser();
    user.setUsername("matteo");
    logger.info("test user {} Admin role: {}", user.getUsername(), userService.isAdmin(user));
  }

  @Test
  public void testAddUser() {
    userService.addUser("testUser", "testUser", "testUser@tsc.it", Role.ROLE_USER);
  }

  @Test
  public void testRemoveUser() {
    userService.removeUser("testUser", Role.ROLE_USER);
  }

  @Test
  public void testRemoveUserFail() {
    userService.removeUser("testUser", Role.ROLE_ADMIN);
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
