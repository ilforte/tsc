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
import it.tsc.model.TscUser;
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
  public TscUser getUser(String username) {
    return userDao.getUser(username);
  }

  public List<TscUser> getAllUsers() {
    return userDao.getAllUsers();
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
  public void addUser(String username, String password, String email, Role role) {
    userDao.addUser(username, password, email, role);
  }

  /*
   * (non-Javadoc)
   * 
   * @see it.tsc.service.UserService#removeUser(java.lang.String, java.lang.String,
   * it.tsc.model.Role, it.tsc.model.User)
   */
  public void removeUser(String username) {
    userDao.removeUser(username);
  }

  /*
   * (non-Javadoc)
   * 
   * @see it.tsc.service.UserService#updateUser(java.lang.String, it.tsc.model.Role,
   * it.tsc.model.User)
   */
  public void updateUser(String username, String password, String email, Role role) {
    userDao.updateUser(username, password, email, role);
  }

  public boolean isAdmin(Role role) {
    return role.equals(Role.ROLE_ADMIN);
  }

  public boolean isAdmin(TscUser user) {
    return getUser(user.getUsername()).getRoles().contains(Role.ROLE_ADMIN.toString());
  }

}
