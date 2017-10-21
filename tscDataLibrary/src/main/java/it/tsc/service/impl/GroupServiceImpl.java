/**
 * 
 */
package it.tsc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.tsc.dao.GroupDao;
import it.tsc.domain.Group;
import it.tsc.service.GroupService;

/**
 * @author astraservice
 *
 */
@Service("groupService")
public class GroupServiceImpl implements GroupService {

  @Autowired
  private GroupDao groupDao;

  /**
   * 
   */
  public GroupServiceImpl() {

  }

  /*
   * (non-Javadoc)
   * 
   * @see it.tsc.service.GroupService#getAllGroup()
   */
  public List<Group> getAllGroups() {
    return groupDao.getAllGroups();
  }

  /*
   * (non-Javadoc)
   * 
   * @see it.tsc.service.GroupService#addGroup(java.lang.String)
   */
  public void addGroup(String groupname) {
    groupDao.addGroup(groupname);
  }

  /*
   * (non-Javadoc)
   * 
   * @see it.tsc.service.GroupService#removeGroup(java.lang.String)
   */
  public void removeGroup(String groupname) {
    groupDao.removeGroup(groupname);
  }

  public void addUserToGroup(String username, String groupname) {
    groupDao.addUserToGroup(username, groupname);
  }

  public void removeUserFromGroup(String username, String groupname) {
    groupDao.removeUserFromGroup(username, groupname);
  }

}
