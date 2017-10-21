/**
 * 
 */
package it.tsc.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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
import it.tsc.domain.Allarm;
import it.tsc.domain.PortalUser;
import it.tsc.domain.Role;
import it.tsc.domain.Users;
import it.tsc.domain.key.CompoundKey;
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
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    TypedQuery<Users> query = entityManager.createNamedQuery(Users.SELECT_BY_USERNAME, Users.class);
    query.setParameter("username", username);
    List<Users> list = query.getResultList();
    entityManager.close();
    
    String result = ConversionUtil.getGsonConverter().toJson(list);
    logger.debug("jsonGetUser {}", result);
    return result;
//    String sql =
//        "SELECT JSON username,role,email FROM ks_tsc.tb_users WHERE username = ? ALLOW FILTERING;";
//    PreparedStatement preparedStmt = baseDao.prepareAndCacheStatement(sql);
//    BoundStatement bound = preparedStmt.bind().setString("username", username);
//    ResultSet resultSet = baseDao.getSession().execute(bound);
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
//    MappingManager manager = baseDao.getMappingManager();
//    PortalUserAccessor userAccessor = manager.createAccessor(PortalUserAccessor.class);
//    Result<PortalUser> rs = userAccessor.getUser(username);
    
	PortalUser PortalUser = null;

    EntityManager entityManager = entityManagerFactory.createEntityManager();
    TypedQuery<Users> query = entityManager.createNamedQuery(Users.SELECT_BY_USERNAME, Users.class);
    query.setParameter("username", username);
    List<Users> list = query.getResultList();
    entityManager.close();
    
    List<String> roles = new ArrayList<String>();
    
    String email = "";
    String password = "";
    String base32Secret = "";
    for (Users user : list) {
      email = user.getEmail();
      password = user.getPassword();
      base32Secret = user.getBase32Secret();
      roles.add(user.getKey().getRole());
    }
    if (roles != null && roles.size() > 0) {
      PortalUser = new PortalUser(username, password, roles, email, base32Secret);
    }
    return PortalUser;
  }

  public PortalUser getUser(String username, String email) {
//    MappingManager manager = baseDao.getMappingManager();
//    PortalUserAccessor userAccessor = manager.createAccessor(PortalUserAccessor.class);
//    Result<PortalUser> rs = userAccessor.getUser(username, email);
    PortalUser PortalUser = null;
    
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    TypedQuery<Users> query = entityManager.createNamedQuery(Users.SELECT_BY_USERNAME_EMAIL, Users.class);
    query.setParameter("username", username);
    query.setParameter("email", email);
    List<Users> list = query.getResultList();
    entityManager.close();
    
    List<String> roles = new ArrayList<String>();
    for (Users user : list) {
      roles.add(user.getKey().getRole());
    }
    if (roles != null && roles.size() > 0) {
      PortalUser = new PortalUser(username, roles, email);
    }
    return PortalUser;
  }

  public List<PortalUser> getAllUsers() {
//    MappingManager manager = baseDao.getMappingManager();
//    PortalUserAccessor userAccessor = manager.createAccessor(PortalUserAccessor.class);
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    TypedQuery<Users> query = entityManager.createNamedQuery(Users.SELECT_ALL_USERS, Users.class);
    List<Users> list = query.getResultList();
    entityManager.close();
	    
    UserTransform t = new UserTransform();
    for (Users user : list) {
      t.addUser(user.getKey().getUsername(), user.getKey().getRole(), user.getEmail());
    }
    return t.getUsers();
  }

  public String jsonGetAllUsers() {
//    String sql = "SELECT JSON username,role,email FROM ks_tsc.tb_users;";
//    ResultSet resultSet = baseDao.getSession().execute(sql);
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    TypedQuery<Users> query = entityManager.createNamedQuery(Users.SELECT_ALL_USERS, Users.class);
    List<Users> list = query.getResultList();
    entityManager.close();
    
    String result = ConversionUtil.getGsonConverter().toJson(list);
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
//    List<GrantedAuthority> roles = null;
//    MappingManager manager = baseDao.getMappingManager();
//    PortalUserAccessor userAccessor = manager.createAccessor(PortalUserAccessor.class);
//    Result<PortalUser> rs = userAccessor.getUserRoles(username);
	List<GrantedAuthority> roles = null;
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    TypedQuery<Users> query = entityManager.createNamedQuery(Users.SELECT_BY_USERNAME, Users.class);
    query.setParameter("username", username);
    List<Users> list = query.getResultList();
    entityManager.close();
	  
    for (Users user : list) {
      if (roles == null) {
        roles = new ArrayList<GrantedAuthority>();
      }
      roles.add(new SimpleGrantedAuthority(user.getKey().getRole()));
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
//    MappingManager manager = baseDao.getMappingManager();
//    PortalUserAccessor userAccessor = manager.createAccessor(PortalUserAccessor.class);
//    ResultSet rs =
//        userAccessor.addUser(username, bcryptEncoder.encode(password), email, role.value(role));
	  
    String sqlString = "INSERT INTO ks_tsc.tb_users (username, password, email,role) "
    		+ "VALUES ('"
    		+ username + "','"
    		+ bcryptEncoder.encode(password) + "','"
    		+ email + "','"
    		+ role.value(role) + "') IF NOT EXISTS";
    
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    Query query = entityManager.createNativeQuery(sqlString,Users.class);
    query.executeUpdate();
    entityManager.close();
    logger.debug("result: {}", ConversionUtil.getGsonConverter().toJson(query));
//    return rs.wasApplied();
    return true;
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
//    MappingManager manager = baseDao.getMappingManager();
//    PortalUserAccessor userAccessor = manager.createAccessor(PortalUserAccessor.class);
//    ResultSet rs = userAccessor.removeUser(username, role.toString());
	Users users = new Users();
	users.addUsername(username);
	users.addRole(role);
	
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.remove(users);
    entityManager.flush();
    entityManager.close();
        
//    logger.debug("result: {}", rs.wasApplied());
//    return rs.wasApplied();
    return true;
  }

  public void updateMfaUserKey(String username, String keyId, String base32Secret, String role) {
    /*
     * PreparedStatement preparedStmt =
     * getSession().prepare("DELETE FROM ks_tsc.tb_users WHERE username = :username;");
     * BoundStatement bound = preparedStmt.bind().setString("username", username); ResultSet rs =
     * getSession().execute(bound); logger.debug("removeUser ExecutionInfo {}",
     * rs.getExecutionInfo());
     */
//    MappingManager manager = baseDao.getMappingManager();
//    PortalUserAccessor userAccessor = manager.createAccessor(PortalUserAccessor.class);
//    userAccessor.updateMfaUserKey(username, keyId, base32Secret, role);
    
    Users users = new Users();
    users.addUsername(username);
    users.addRole(Role.valueOf(role));
    users.setKeyId(keyId);
    users.setBase32Secret(base32Secret);
    
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    TypedQuery<Users> query = entityManager.createNamedQuery(Users.UPDATE_BY_USERNAME_ROLE, Users.class);
    query.setParameter("username", username);
    query.setParameter("role", role);
    query.setParameter("keyId", keyId);
    query.setParameter("base32Secret", base32Secret);
    query.executeUpdate();
    entityManager.close();
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
//    MappingManager manager = baseDao.getMappingManager();
//    PortalUserAccessor userAccessor = manager.createAccessor(PortalUserAccessor.class);
//    userAccessor.updateUser(username, password, email, role.value(role));
	    EntityManager entityManager = entityManagerFactory.createEntityManager();
	    TypedQuery<Users> query = entityManager.createNamedQuery(Users.UPDATE_USER, Users.class);
	    query.setParameter("username", username);
	    query.setParameter("password", password);
	    query.setParameter("email", email);
	    query.setParameter("role", role.toString());
	    query.executeUpdate();
	    entityManager.close();
    
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
