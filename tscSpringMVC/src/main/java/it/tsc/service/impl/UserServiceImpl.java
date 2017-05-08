/**
 * 
 */
package it.tsc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import it.tsc.dao.UserDao;
import it.tsc.model.ApplicationUser;
import it.tsc.model.Role;
import it.tsc.service.UserService;

/**
 * @author astraservice
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService {

  @Autowired
  private UserDao userDao;

  /**
   * 
   */
  public UserServiceImpl() {
    // TODO Auto-generated constructor stub
  }

  /*
   * (non-Javadoc)
   * 
   * @see it.tsc.service.UserService#getUserRole(java.lang.String)
   */
  public ApplicationUser getUser(String username) {
    return userDao.getUser(username);
  }

  /*
   * (non-Javadoc)
   * 
   * @see it.tsc.service.UserService#getUserRoles(java.lang.String,java.lang.String)
   */
  public List<GrantedAuthority> getUserRoles(String username, String password) {
    return userDao.getUserRoles(username, password);
  }

  /*
   * (non-Javadoc)
   * 
   * @see it.tsc.service.UserService#isAdmin(it.tsc.model.Role)
   */
  public boolean isAdmin(ApplicationUser requester) {
    return requester.getAuthorities() != null
        && requester.getAuthorities().contains(Role.ROLE_ADMIN) ? true : false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see it.tsc.service.UserService#addUser(java.lang.String, java.lang.String, it.tsc.model.Role,
   * it.tsc.model.User)
   */
  public void addUser(String username, String password, Role role, ApplicationUser requester) {
    if (isAdmin(requester)) {

    } else {

    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see it.tsc.service.UserService#removeUser(java.lang.String, java.lang.String,
   * it.tsc.model.Role, it.tsc.model.User)
   */
  public void removeUser(String username, String password, Role role, ApplicationUser requester) {
    if (isAdmin(requester)) {

    } else {

    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see it.tsc.service.UserService#updateUser(java.lang.String, it.tsc.model.Role,
   * it.tsc.model.User)
   */
  public void updateUser(String username, Role role, ApplicationUser requester) {
    if (isAdmin(requester)) {

    } else {

    }
  }

  public boolean isAdmin(Role role) {
    return false;
  }

}
