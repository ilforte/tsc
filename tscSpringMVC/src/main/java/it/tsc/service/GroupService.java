/**
 * 
 */
package it.tsc.service;

import java.util.List;

import it.tsc.model.Group;

/**
 * @author astraservice
 *
 */
public interface GroupService {
  /**
   * get All Group
   * 
   * @return
   */
  public List<Group> getAllGroups();

  /**
   * add Group
   * 
   * @param groupname
   */
  public void addGroup(String groupname);

  /**
   * remove Group
   * 
   * @param groupname
   */
  public void removeGroup(String groupname);

  /**
   * add TscUser to Group
   * 
   * @param username
   * @param groupname
   */
  public void addUserToGroup(String username, String groupname);

  /**
   * remove TscUser from Group
   * 
   * @param username
   * @param groupname
   */
  public void removeUserFromGroup(String username, String groupname);
}
