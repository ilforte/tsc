/**
 * 
 */
package it.tsc.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;

import it.tsc.dao.AbstractDataAccess;
import it.tsc.dao.UserDao;
import it.tsc.model.ApplicationUser;
import it.tsc.model.Role;

/**
 * @author astraservice
 *
 */
@Repository("userDao")
public class UserDaoImpl extends AbstractDataAccess implements UserDao {

  /**
   * to convert v
   * 
   */
  public UserDaoImpl() {

  }

  /*
   * extends CommonDao (non-Javadoc)
   * 
   * @see it.tsc.dao.UserDao#getUserRole(java.lang.String)
   */
  public ApplicationUser getUser(String username) {
    ApplicationUser user = null;
    PreparedStatement preparedStmt =
        getSession().prepare("SELECT * FROM ks_tsc.tb_users WHERE username = ? ALLOW FILTERING;");
    BoundStatement bound = preparedStmt.bind().setString("username", username);
    ResultSet rs = getSession().execute(bound);
    for (Row row : rs) {
      if (user == null) {
        List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
        roles.add(new SimpleGrantedAuthority(Role.valueOf(row.getString("role")).toString()));
        user = new ApplicationUser(row.getString("username"), row.getString("password"), true, true,
            true, true, roles, "TestEmail");
      }
      return user;
    }
    return user;
  }

  /*
   * (non-Javadoc) extends CommonDao
   * 
   * @see it.tsc.dao.UserDao#getUserRoles(java.lang.String,java.lang.String)
   */
  public List<GrantedAuthority> getUserRoles(String username, String password) {
    List<GrantedAuthority> roles = null;
    PreparedStatement preparedStmt = getSession().prepare(
        "SELECT * FROM ks_tsc.tb_users WHERE username = ? AND password = ? ALLOW FILTERING;");
    BoundStatement bound =
        preparedStmt.bind().setString("username", username).setString("password", password);
    ResultSet rs = getSession().execute(bound);
    for (Row row : rs) {
      if (roles == null) {
        roles = new ArrayList<GrantedAuthority>();
      }
      String role = row.getString("role");
      roles.add(new SimpleGrantedAuthority(Role.valueOf(role).toString()));
    }
    return roles;
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
