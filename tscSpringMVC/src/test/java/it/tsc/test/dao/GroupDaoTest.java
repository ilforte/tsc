/**
 * 
 */
package it.tsc.test.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
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
@Configuration
@ImportResource(value = {"classpath:spring-beans.xml"})
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
