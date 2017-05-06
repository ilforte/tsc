/**
 * 
 */
package it.tsc.service;

import it.tsc.model.Role;
import it.tsc.model.User;

/**
 * @author astraservice
 *
 */
public interface UserService {

  /**
   * get User Role
   * 
   * @param username
   * @return
   */
  public Role getUserRole(String username);

  /**
   * Determines if User have Admin role
   * 
   * @param role
   * @return
   */
  public boolean isAdmin(Role role);

  /**
   * Add user
   * 
   * @param username
   * @param password
   * @param role
   * @param requester (Only admin role is permitted)
   */
  public void addUser(String username, String password, Role role, User requester);

  /**
   * Remove user
   * 
   * @param username
   * @param password
   * @param role
   * @param requester (Only admin role is permitted)
   */
  public void removeUser(String username, String password, Role role, User requester);

  /**
   * update user
   * 
   * @param username
   * @param role
   * @param requester (Only admin role is permitted)
   */
  public void updateUser(String username, Role role, User requester);

}
