/**
 * 
 */
package it.tsc.service;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import it.tsc.model.PortalUser;
import it.tsc.model.Role;

/**
 * @author astraservice
 *
 */
public interface UserService {

  /**
   * get User object
   * 
   * @param username
   * @return
   */
  public PortalUser getUser(String username);

  /**
   * get User object (renew pwd service)
   * 
   * @param username
   * @param email
   * @return
   */
  public PortalUser getUser(String username, String email);

  /**
   * get User in JSON format
   * 
   * @param username
   * @return
   */
  public String jsonGetUser(String username);

  /**
   * get All Users
   * 
   * @return (Only for admin role is permitted)
   */
  public List<PortalUser> getAllUsers();

  /**
   * get All Users in JSON format
   * 
   * @return (Only for admin role is permitted)
   */
  public String jsonGetAllUsers();

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
   * Determines if User have Admin role
   * 
   * @param user
   * @return
   */
  public boolean isAdmin(PortalUser user);

  /**
   * Add user
   * 
   * @param username (Only admin role is permitted)
   * @param password
   * @param email
   * @param role
   * @return
   */
  public boolean addUser(String username, String password, String email, Role role);

  /**
   * Remove user
   * 
   * @param username
   * @param role
   * @return
   */
  public boolean removeUser(String username, Role role);

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
