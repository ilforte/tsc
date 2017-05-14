/**
 * 
 */
package it.tsc.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import com.datastax.driver.mapping.MappingManager;
import com.datastax.driver.mapping.Result;

import it.tsc.accessor.TscUserAccessor;
import it.tsc.dao.BaseDao;
import it.tsc.dao.UserDao;
import it.tsc.model.Role;
import it.tsc.model.TscUser;
import it.tsc.util.UserTransform;

/**
 * @author astraservice
 *
 */
@Repository("userDao")
public class UserDaoImpl implements UserDao {
  private static Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
  @Autowired
  private BaseDao baseDao;

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
  public TscUser getUser(String username) {
    /*
     * TscUser user = null; PreparedStatement preparedStmt =
     * getSession().prepare("SELECT * FROM ks_tsc.tb_users WHERE username = ? ALLOW FILTERING;");
     * BoundStatement bound = preparedStmt.bind().setString("username", username); ResultSet rs =
     * getSession().execute(bound); List<String> roles = new ArrayList<String>(); String email = "";
     * for (Row row : rs) { email = row.getString("email"); roles.add(row.getString("role")); } if
     * (roles.size() > 0) { user = new TscUser(username, roles, email); } return user;
     */
    TscUser tscUser = null;
    MappingManager manager = baseDao.getMappingManager();
    TscUserAccessor userAccessor = manager.createAccessor(TscUserAccessor.class);
    Result<TscUser> rs = userAccessor.getUser(username);
    List<String> roles = new ArrayList<String>();
    String email = "";
    for (TscUser user : rs.all()) {
      email = user.getEmail();
      roles.add(user.getRole());
    }
    if (roles.size() > 0) {
      tscUser = new TscUser(username, roles, email);
    }
    return tscUser;
  }

  public List<TscUser> getAllUsers() {
    MappingManager manager = baseDao.getMappingManager();
    TscUserAccessor userAccessor = manager.createAccessor(TscUserAccessor.class);
    Result<TscUser> rs = userAccessor.getAllUsers();
    UserTransform t = new UserTransform();
    for (TscUser user : rs.all()) {
      t.addUser(user.getUsername(), user.getRole(), user.getEmail());
    }
    return t.getUsers();
  }

  /*
   * (non-Javadoc) extends CommonDao
   * 
   * @see it.tsc.dao.UserDao#getUserRoles(java.lang.String,java.lang.String)
   */
  public List<GrantedAuthority> getUserRoles(String username, String password) {
    /*
     * List<GrantedAuthority> roles = null; PreparedStatement preparedStmt = getSession().prepare(
     * "SELECT * FROM ks_tsc.tb_users WHERE username = ? AND password = ? ALLOW FILTERING;");
     * BoundStatement bound = preparedStmt.bind().setString("username",
     * username).setString("password", password); ResultSet rs = getSession().execute(bound); for
     * (Row row : rs) { if (roles == null) { roles = new ArrayList<GrantedAuthority>(); } String
     * role = row.getString("role"); roles.add(new
     * SimpleGrantedAuthority(Role.valueOf(role).toString())); }
     * logger.debug("getUserRoles ExecutionInfo {}", rs.getExecutionInfo());
     */
    List<GrantedAuthority> roles = null;
    MappingManager manager = baseDao.getMappingManager();
    TscUserAccessor userAccessor = manager.createAccessor(TscUserAccessor.class);
    Result<TscUser> rs = userAccessor.getUserRoles(username, password);
    for (TscUser user : rs.all()) {
      if (roles == null) {
        roles = new ArrayList<GrantedAuthority>();
      }
      roles.add(new SimpleGrantedAuthority(user.getRole()));
    }
    return roles;
  }

  /*
   * (non-Javadoc)
   * 
   * @see it.tsc.dao.UserDao#isAdmin(it.tsc.model.Role)
   */
  public boolean isAdmin(Role role) {
    return role.equals(Role.ROLE_ADMIN);
  }

  /*
   * (non-Javadoc)
   * 
   * @see it.tsc.dao.UserDao#addUser(java.lang.String, java.lang.String,, java.lang.String
   * it.tsc.model.Role)
   */
  public void addUser(String username, String password, String email, Role role) {
    /*
     PreparedStatement preparedStmt = getSession().prepare(
     "INSERT INTO ks_tsc.tb_users (username, password, email,role) VALUES (:username, :password,
     :email,:role) IF NOT EXISTS;");
     preparedStmt.setConsistencyLevel(ConsistencyLevel.ONE);
    // @formatter:off
    BoundStatement bound = preparedStmt.bind().setString("username", username)
        .setString("password", password)
        .setString("email", email)
        .setString("role", role.value(role));
    // @formatter:on
     ResultSet rs = getSession().execute(bound);
     */
    MappingManager manager = baseDao.getMappingManager();
    TscUserAccessor userAccessor = manager.createAccessor(TscUserAccessor.class);
    userAccessor.addUser(username, password, email, role.value(role));
  }

  /*
   * (non-Javadoc) public List<GrantedAuthority> getUserRoles(String username, String password) { //
   * 
   * @see it.tsc.dao.UserDao#removeUser(java.lang.String)
   */
  public void removeUser(String username) {
    /*
     * PreparedStatement preparedStmt =
     * getSession().prepare("DELETE FROM ks_tsc.tb_users WHERE username = :username;");
     * BoundStatement bound = preparedStmt.bind().setString("username", username); ResultSet rs =
     * getSession().execute(bound); logger.debug("removeUser ExecutionInfo {}",
     * rs.getExecutionInfo());
     */
    MappingManager manager = baseDao.getMappingManager();
    TscUserAccessor userAccessor = manager.createAccessor(TscUserAccessor.class);
    userAccessor.removeUser(username);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * it.tsc.dao.UserDao#updateUser(java.lang.String,java.lang.String,java.lang.String,it.tsc.model.
   * Role)
   */
  public void updateUser(String username, String password, String email, Role role) {
    /*
     PreparedStatement preparedStmt =
     getSession().prepare("UPDATE ks_tsc.tb_users SET password = :password,email = :email "
     + "WHERE username = :username AND role = :role IF EXISTS;");
    // @formatter:off
    BoundStatement bound = preparedStmt.bind()
        .setString("password", password)
        .setString("email", email)
        .setString("role", role.value(role))
        .setString("username", username);
    // @formatter:on
     ResultSet rs = getSession().execute(bound);
     logger.debug("addUser ExecutionInfo {}", rs.getExecutionInfo());
     */
    MappingManager manager = baseDao.getMappingManager();
    TscUserAccessor userAccessor = manager.createAccessor(TscUserAccessor.class);
    userAccessor.updateUser(username, password, email, role.value(role));
  }

}
