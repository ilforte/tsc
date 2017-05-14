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
import it.tsc.service.GroupService;

/**
 * @author astraservice
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
// ApplicationContext will be loaded from the OrderServiceConfig class
@ContextConfiguration(classes = ServiceConfig.class, loader = AnnotationConfigContextLoader.class)
public class GroupDaoTest {
  private static Logger logger = LoggerFactory.getLogger(GroupDaoTest.class);
  @Autowired
  private GroupService groupService;
  private Gson gson = new Gson();

  /**
   * 
   */
  public GroupDaoTest() {}

  @Test
  public void testGroupDao() {
    logger.info("groupService group {}", gson.toJson(groupService.getAllGroups()));
  }

  @Test
  public void testAddGroup() {
    groupService.addGroup("MILANO");
  }

  @Test
  public void testRemoveGroup() {
    groupService.removeGroup("MILANO");
  }

  @Test
  public void testAddUserToGroup() {
    groupService.addUserToGroup("matteo", "MILANO");
  }

  @Test
  public void testRemoveUserFromGroup() {
    groupService.removeUserFromGroup("matteo", "MILANO");
  }
}
