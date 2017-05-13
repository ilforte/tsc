package it.tsc.dao;

import java.util.List;

import it.tsc.model.Group;

public interface GroupDao {
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
}
