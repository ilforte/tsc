/**
 * 
 */
package it.tsc.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Repository;

import it.tsc.dao.CommonDao;
import it.tsc.dao.UserDao;
import it.tsc.model.ApplicationUser;
import it.tsc.model.Role;

/**
 * @author astraservice
 *
 */
@Repository("userDao")
public class UserDaoImpl implements UserDao {

  @Autowired
  private CommonDao commonDao;

  /**
   * to convert v
   * 
   */
  public UserDaoImpl() {

  }

  /*
   * (non-Javadoc)
   * 
   * @see it.tsc.dao.UserDao#getUserRole(java.lang.String)
   */
  public ApplicationUser getUser(String username) {

    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see it.tsc.dao.UserDao#getUserRoles(java.lang.String,java.lang.String)
   */
  public List<GrantedAuthority> getUserRoles(String username, String password) {
    commonDao.getSession();
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see it.tsc.dao.UserDao#isAdmin(it.tsc.model.Role)
   */
  public boolean isAdmin(Role role) {

    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see it.tsc.dao.UserDao#addUser(java.lang.String, java.lang.String, it.tsc.model.Role,
   * it.tsc.model.User)
   */
  public void addUser(String username, String password, Role role, ApplicationUser requester) {


  }

  /*
   * (non-Javadoc) public List<GrantedAuthority> getUserRoles(String username, String password) { //
   * TODO Auto-generated method stub return null; }
   * 
   * @see it.tsc.dao.UserDao#removeUser(java.lang.String, java.lang.String, it.tsc.model.Role,
   * it.tsc.model.User)
   */
  public void removeUser(String username, String password, Role role, ApplicationUser requester) {


  }

  /*
   * (non-Javadoc)
   * 
   * @see it.tsc.dao.UserDao#updateUser(java.lang.String, it.tsc.model.Role, it.tsc.model.User)
   */
  public void updateUser(String username, Role role, ApplicationUser requester) {


  }

}
