/**
 * 
 */
package it.tsc.dao;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import it.tsc.model.PortalUser;
import it.tsc.model.Role;

/**
 * @author astraservice
 *
 */
public interface UserDao {
  /**
   * get User in JSON format
   * 
   * @param username
   * @return
   */
  public String jsonGetUser(String username);

  /**
   * get User Object
   * 
   * @param username
   * @return
   */
  public PortalUser getUser(String username);

  /**
   * get All Users in JSON format
   * 
   * @return (Only for admin role is permitted)
   */
  public String jsonGetAllUsers();

  /**
   * get All Users
   * 
   * @return (Only for admin role is permitted)
   */
  public List<PortalUser> getAllUsers();

  /**
   * Return Roles for user
   * 
   * @param username
   * @return
   */
  public List<GrantedAuthority> getUserRoles(String username);

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
