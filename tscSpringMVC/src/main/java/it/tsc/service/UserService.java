/**
 * 
 */
package it.tsc.service;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import it.tsc.model.Role;
import it.tsc.model.TscUser;

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
  public TscUser getUser(String username);

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
   * Determines if User have Admin role
   * 
   * @param user
   * @return
   */
  public boolean isAdmin(TscUser user);

  /**
   * Add user
   * 
   * @param username (Only admin role is permitted)
   * @param password
   * @param email
   * @param role
   */
  public void addUser(String username, String password, String email, Role role);

  /**
   * Remove user
   * 
   * @param username (Only admin role is permitted)
   */
  public void removeUser(String username);

  /**
   * update user
   * 
   * @param username (Only admin role is permitted)
   * @param password
   * @param email
   * @param role
   */
  public void updateUser(String username, String password, String email, Role role);

}
