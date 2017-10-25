/**
 * 
 */
package it.tsc.test.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;

import it.tsc.domain.PortalUser;
import it.tsc.domain.Role;
import it.tsc.service.UserService;

/**
 * @author astraservice
 *
 */
public class UserDaoTest extends BaseDaoTest {
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
    logger.info("userService user {}", gson.toJson(userService.getUser("testUser")));
  }

  @Test
  public void testUserEmailDao() {
    userService.getUser("matteo", "test");
    assertTrue(userService.getUser("matteo", "test") == null);
    // assertTrue(userService.getUser("matteo", "matteo@infamiglia.it") != null);
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
    user.setUsername("matteo");
    assertNotNull(userService.isAdmin(user));
  }

  @Test
  public void testSuperAdmin() {
    PortalUser user = new PortalUser();
    user.setUsername("admin");
    assertTrue(userService.isSuperAdmin(user));
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

  // @Test
  // public void testRemoveUserAdmin() {
  // userService.removeUser("admin", Role.ROLE_SADMIN);
  // }

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

  @Test
  public void testUpdateMfaUserKey() {
    userService.updateMfaUserKey("admin", "testUser@tsc.it", "fwefwefwegeg");
  }

  @Test
  public void testUpdateEmptyMfaUserKey() {
    userService.updateMfaUserKey("admin", "", "");
  }

  @Test
  public void jsonGestUser() {
    userService.jsonGetUser("admin");
  }


}
