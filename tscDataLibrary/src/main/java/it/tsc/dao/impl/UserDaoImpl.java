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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.mapping.MappingManager;
import com.datastax.driver.mapping.Result;

import it.tsc.dao.AbstractDao;
import it.tsc.dao.UserDao;
import it.tsc.dao.accessor.PortalUserAccessor;
import it.tsc.domain.PortalUser;
import it.tsc.domain.Role;
import it.tsc.util.ConversionUtil;
import it.tsc.util.UserTransform;

/**
 * @author astraservice
 *
 */
@Repository("userDao")
public class UserDaoImpl extends AbstractDao implements UserDao {
  private static Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
  @Autowired
  private BCryptPasswordEncoder bcryptEncoder;

  /**
   * to convert v
   * 
   */
  public UserDaoImpl() {

  }

  public String jsonGetUser(String username) {
    String sql =
        "SELECT JSON username,role,email FROM ks_tsc.tb_users WHERE username = ? ALLOW FILTERING;";
    PreparedStatement preparedStmt = baseDao.prepareAndCacheStatement(sql);
    BoundStatement bound = preparedStmt.bind().setString("username", username);
    ResultSet resultSet = baseDao.getSession().execute(bound);
    String result = ConversionUtil.returnJson(resultSet.all());
    logger.debug("jsonGetUser {}", result);
    return result;
  }

  /*
   * extends CommonDao (non-Javadoc)
   * 
   * @see it.tsc.dao.UserDao#getUserRole(java.lang.String)
   */
  public PortalUser getUser(String username) {
    /*
     * PortalUser user = null; PreparedStatement preparedStmt =
     * getSession().prepare("SELECT * FROM ks_tsc.tb_users WHERE username = ? ALLOW FILTERING;");
     * BoundStatement bound = preparedStmt.bind().setString("username", username); ResultSet rs =
     * getSession().execute(bound); List<String> roles = new ArrayList<String>(); String email = "";
     * for (Row row : rs) { email = row.getString("email"); roles.add(row.getString("role")); } if
     * (roles.size() > 0) { user = new PortalUser(username, roles, email); } return user;
     */
    PortalUser PortalUser = null;
    MappingManager manager = baseDao.getMappingManager();
    PortalUserAccessor userAccessor = manager.createAccessor(PortalUserAccessor.class);
    Result<PortalUser> rs = userAccessor.getUser(username);
    List<String> roles = new ArrayList<String>();
    String email = "";
    String password = "";
    String base32Secret = "";
    for (PortalUser user : rs.all()) {
      email = user.getEmail();
      password = user.getPassword();
      base32Secret = user.getBase32Secret();
      roles.add(user.getRole());
    }
    if (roles != null && roles.size() > 0) {
      PortalUser = new PortalUser(username, password, roles, email, base32Secret);
    }
    return PortalUser;
  }

  public PortalUser getUser(String username, String email) {
    PortalUser PortalUser = null;
    MappingManager manager = baseDao.getMappingManager();
    PortalUserAccessor userAccessor = manager.createAccessor(PortalUserAccessor.class);
    Result<PortalUser> rs = userAccessor.getUser(username, email);
    List<String> roles = new ArrayList<String>();
    for (PortalUser user : rs.all()) {
      roles.add(user.getRole());
    }
    if (roles != null && roles.size() > 0) {
      PortalUser = new PortalUser(username, roles, email);
    }
    return PortalUser;
  }

  public List<PortalUser> getAllUsers() {
    MappingManager manager = baseDao.getMappingManager();
    PortalUserAccessor userAccessor = manager.createAccessor(PortalUserAccessor.class);
    Result<PortalUser> rs = userAccessor.getAllUsers();
    UserTransform t = new UserTransform();
    for (PortalUser user : rs.all()) {
      t.addUser(user.getUsername(), user.getRole(), user.getEmail());
    }
    return t.getUsers();
  }

  public String jsonGetAllUsers() {
    String sql = "SELECT JSON username,role,email FROM ks_tsc.tb_users;";
    ResultSet resultSet = baseDao.getSession().execute(sql);
    String result = ConversionUtil.returnJson(resultSet.all());
    logger.debug("jsonGetAllUsers {}", result);
    return result;
  }

  /*
   * (non-Javadoc) extends CommonDao
   * 
   * @see it.tsc.dao.UserDao#getUserRoles(java.lang.String,java.lang.String)
   */
  public List<GrantedAuthority> getUserRoles(String username) {
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
    PortalUserAccessor userAccessor = manager.createAccessor(PortalUserAccessor.class);
    Result<PortalUser> rs = userAccessor.getUserRoles(username);
    for (PortalUser user : rs.all()) {
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
    if (role != null) {
      return role.equals(Role.ROLE_ADMIN) || role.equals(Role.ROLE_SADMIN);
    } else {
      return false;
    }
    
  }

  /*
   * (non-Javadoc)
   * 
   * @see it.tsc.dao.UserDao#addUser(java.lang.String, java.lang.String,, java.lang.String
   * it.tsc.model.Role)
   */
  public boolean addUser(String username, String password, String email, Role role) {
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
    PortalUserAccessor userAccessor = manager.createAccessor(PortalUserAccessor.class);
    ResultSet rs =
        userAccessor.addUser(username, bcryptEncoder.encode(password), email, role.value(role));
    logger.debug("result: {}", rs.wasApplied());
    return rs.wasApplied();
  }

  /*
   * (non-Javadoc) public List<GrantedAuthority> getUserRoles(String username, String password) { //
   * 
   * @see it.tsc.dao.UserDao#removeUser(java.lang.String)
   */
  public boolean removeUser(String username, Role role) {
    /*
     * PreparedStatement preparedStmt =
     * getSession().prepare("DELETE FROM ks_tsc.tb_users WHERE username = :username;");
     * BoundStatement bound = preparedStmt.bind().setString("username", username); ResultSet rs =
     * getSession().execute(bound); logger.debug("removeUser ExecutionInfo {}",
     * rs.getExecutionInfo());
     */
    MappingManager manager = baseDao.getMappingManager();
    PortalUserAccessor userAccessor = manager.createAccessor(PortalUserAccessor.class);
    ResultSet rs = userAccessor.removeUser(username, role.toString());
    logger.debug("result: {}", rs.wasApplied());
    return rs.wasApplied();
  }

  public void updateMfaUserKey(String username, String keyId, String base32Secret, String role) {
    /*
     * PreparedStatement preparedStmt =
     * getSession().prepare("DELETE FROM ks_tsc.tb_users WHERE username = :username;");
     * BoundStatement bound = preparedStmt.bind().setString("username", username); ResultSet rs =
     * getSession().execute(bound); logger.debug("removeUser ExecutionInfo {}",
     * rs.getExecutionInfo());
     */
    MappingManager manager = baseDao.getMappingManager();
    PortalUserAccessor userAccessor = manager.createAccessor(PortalUserAccessor.class);
    userAccessor.updateMfaUserKey(username, keyId, base32Secret, role);
  }

  /*
   * (non-Javadoc)
   * 
   * @Override public PortalUser getUser(String username, String email) { // TODO Auto-generated
   * method stub return null; }*
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
    @Override
  public PortalUser getUser(String username, String email) {
    // TODO Auto-generated method stub
    return null;
  }      .setString("password", password)
        .setString("email", email)
        .setString("role", role.value(role))
        .setString("username", username);
    // @formatter:on
     ResultSet rs = getSession().execute(bound);
     logger.debug("addUser ExecutionInfo {}", rs.getExecutionInfo());
     */
    MappingManager manager = baseDao.getMappingManager();
    PortalUserAccessor userAccessor = manager.createAccessor(PortalUserAccessor.class);
    userAccessor.updateUser(username, password, email, role.value(role));
  }

  /**
   * map return result to JSON
   * 
   * @return
   */
  @SuppressWarnings("unused")
  private String returnJson(Row row) {
    return row.getString("[json]");
  }

}
