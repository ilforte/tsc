/**
 * 
 */
package it.tsc.service.impl;

import it.tsc.model.Role;
import it.tsc.model.User;
import it.tsc.service.UserService;

/**
 * @author astraservice
 *
 */
public class UserServiceImpl implements UserService {

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
  public Role getUserRole(String username) {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see it.tsc.service.UserService#isAdmin(it.tsc.model.Role)
   */
  public boolean isAdmin(Role role) {
    return role.equals(Role.ROLE_ADMIN);
  }

  /*
   * (non-Javadoc)
   * 
   * @see it.tsc.service.UserService#addUser(java.lang.String, java.lang.String, it.tsc.model.Role,
   * it.tsc.model.User)
   */
  public void addUser(String username, String password, Role role, User requester) {
    if (isPermitted(requester)) {

    } else {

    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see it.tsc.service.UserService#removeUser(java.lang.String, java.lang.String,
   * it.tsc.model.Role, it.tsc.model.User)
   */
  public void removeUser(String username, String password, Role role, User requester) {
    if (isPermitted(requester)) {

    } else {

    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see it.tsc.service.UserService#updateUser(java.lang.String, it.tsc.model.Role,
   * it.tsc.model.User)
   */
  public void updateUser(String username, Role role, User requester) {
    if (isPermitted(requester)) {

    } else {

    }
  }

  /**
   * check if user have permission
   * 
   * @param requester
   * @return
   */
  private boolean isPermitted(User requester) {
    return getUserRole(requester.getUsername()).equals(isAdmin(requester.getRole()));
  }

}
