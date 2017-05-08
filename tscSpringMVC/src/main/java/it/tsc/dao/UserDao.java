/**
 * 
 */
package it.tsc.dao;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import it.tsc.model.ApplicationUser;
import it.tsc.model.Role;

/**
 * @author astraservice
 *
 */
public interface UserDao {

  /**
   * get User Role
   * 
   * @param username
   * @return
   */
  public ApplicationUser getUser(String username);

  /**
   * Return Roles for user
   * 
   * @param username
   * @return
   */
  public List<GrantedAuthority> getUserRoles(String username, String password);

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
  public void addUser(String username, String password, Role role, ApplicationUser requester);

  /**
   * Remove user
   * 
   * @param username
   * @param password
   * @param role
   * @param requester (Only admin role is permitted)
   */
  public void removeUser(String username, String password, Role role, ApplicationUser requester);

  /**
   * update user
   * 
   * @param username
   * @param role
   * @param requester (Only admin role is permitted)
   */
  public void updateUser(String username, Role role, ApplicationUser requester);
}
