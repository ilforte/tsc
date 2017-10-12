/**
 * 
 */
package it.tsc.dao.impl;

import java.util.List;

import it.tsc.dao.GroupDao;
import it.tsc.model.Group;

/**
 * @author astraservice
 *
 */
public class GroupDaoImpl implements GroupDao {

  /* (non-Javadoc)
   * @see it.tsc.dao.GroupDao#getAllGroups()
   */
  @Override
  public List<Group> getAllGroups() {
    // TODO Auto-generated method stub
    return null;
  }

  /* (non-Javadoc)
   * @see it.tsc.dao.GroupDao#addGroup(java.lang.String)
   */
  @Override
  public void addGroup(String groupname) {

  }

  /* (non-Javadoc)
   * @see it.tsc.dao.GroupDao#removeGroup(java.lang.String)
   */
  @Override
  public void removeGroup(String groupname) {

  }

  /* (non-Javadoc)
   * @see it.tsc.dao.GroupDao#addUserToGroup(java.lang.String, java.lang.String)
   */
  @Override
  public void addUserToGroup(String username, String groupname) {

  }

  /* (non-Javadoc)
   * @see it.tsc.dao.GroupDao#removeUserFromGroup(java.lang.String, java.lang.String)
   */
  @Override
  public void removeUserFromGroup(String username, String groupname) {

  }

}
