/**
 * 
 */
package it.tsc.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.datastax.driver.core.Row;

import it.tsc.dao.BaseDao;
import it.tsc.dao.UserDao;
import it.tsc.domain.PortalUser;
import it.tsc.domain.Role;
import it.tsc.domain.Users;
import it.tsc.domain.key.CompoundKey;
import it.tsc.util.JsonUtil;
import it.tsc.util.UserTransform;

/**
 * @author astraservice
 *
 */
@Repository("userDao")
public class UserDaoImpl extends BaseDao implements UserDao {
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
		EntityManager entityManager = getEntityManager();
		TypedQuery<Users> query = entityManager.createNamedQuery(Users.SELECT_BY_USERNAME, Users.class);
		query.setParameter("username", username);
		List<Users> list = query.getResultList();
		// entityManager.close();

		String result = JsonUtil.getGsonConverter().toJson(list);
		logger.debug("jsonGetUser {}", result);
		return result;
	}

	/*
	 * extends CommonDao (non-Javadoc)
	 * 
	 * @see it.tsc.dao.UserDao#getUserRole(java.lang.String)
	 */
	public PortalUser getUser(String username) {
		PortalUser PortalUser = null;

		EntityManager entityManager = getEntityManager();
		TypedQuery<Users> query = entityManager.createNamedQuery(Users.SELECT_BY_USERNAME, Users.class);
		query.setParameter("username", username);
		List<Users> list = query.getResultList();
		// entityManager.close();

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
		PortalUser PortalUser = null;

		EntityManager entityManager = getEntityManager();
		TypedQuery<Users> query = entityManager.createNamedQuery(Users.SELECT_BY_USERNAME_EMAIL, Users.class);
		query.setParameter("username", username);
		query.setParameter("email", email);
		List<Users> list = query.getResultList();
		// entityManager.close();

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
		// MappingManager manager = baseDao.getMappingManager();
		// PortalUserAccessor userAccessor =
		// manager.createAccessor(PortalUserAccessor.class);
		EntityManager entityManager = getEntityManager();
		TypedQuery<Users> query = entityManager.createNamedQuery(Users.SELECT_ALL_USERS, Users.class);
		List<Users> list = query.getResultList();
		// entityManager.close();

		UserTransform t = new UserTransform();
		for (Users user : list) {
			t.addUser(user.getKey().getUsername(), user.getKey().getRole(), user.getEmail());
		}
		return t.getUsers();
	}

	public String jsonGetAllUsers() {
		// String sql = "SELECT JSON username,role,email FROM ks_tsc.tb_users;";
		// ResultSet resultSet = baseDao.getSession().execute(sql);
		EntityManager entityManager = getEntityManager();
		TypedQuery<Users> query = entityManager.createNamedQuery(Users.SELECT_ALL_USERS, Users.class);
		List<Users> list = query.getResultList();
		// entityManager.close();

		String result = JsonUtil.getGsonConverter().toJson(list);
		logger.debug("jsonGetAllUsers {}", result);
		return result;
	}

	/*
	 * (non-Javadoc) extends CommonDao
	 * 
	 * @see it.tsc.dao.UserDao#getUserRoles(java.lang.String,java.lang.String)
	 */
	public List<GrantedAuthority> getUserRoles(String username) {
		List<GrantedAuthority> roles = null;
		EntityManager entityManager = getEntityManager();
		TypedQuery<Users> query = entityManager.createNamedQuery(Users.SELECT_BY_USERNAME, Users.class);
		query.setParameter("username", username);
		List<Users> list = query.getResultList();
		// entityManager.close();

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
	 * @see it.tsc.dao.UserDao#addUser(java.lang.String, java.lang.String,,
	 * java.lang.String it.tsc.model.Role)
	 */
	public boolean addUser(String username, String password, String email, Role role) {
		Validate.notEmpty(password, "Password cannot be empty");
		Users user = new Users(new CompoundKey(username, role), bcryptEncoder.encode(password), email);
		EntityManager entityManager = getEntityManager();
		entityManager.persist(user);
		// entityManager.close();
		logger.debug("result: {}", user);
		// return rs.wasApplied();
		return true;
	}

	/*
	 * (non-Javadoc) public List<GrantedAuthority> getUserRoles(String username,
	 * String password) { //
	 * 
	 * @see it.tsc.dao.UserDao#removeUser(java.lang.String)
	 */
	public boolean removeUser(String username, Role role) {
		String sql = "DELETE FROM ks_tsc.tb_users WHERE username='" + username + "' AND role ='" + role.toString()
				+ "' IF EXISTS";
		Query query = getEntityManager().createNativeQuery(sql, Users.class);
		query.executeUpdate();
		// entityManager.close();

		// logger.debug("result: {}", rs.wasApplied());
		// return rs.wasApplied();
		return true;
	}

	public void updateMfaUserKey(String username, String keyId, String base32Secret, String role) {
		Users users = new Users(new CompoundKey(username, role), true, keyId, base32Secret);
		EntityManager entityManager = getEntityManager();
		TypedQuery<Users> query = entityManager.createNamedQuery(Users.UPDATE_BY_USERNAME_ROLE, Users.class);
		query.setParameter("username", username);
		query.setParameter("role", role);
		query.setParameter("keyId", keyId);
		query.setParameter("base32Secret", base32Secret);
		query.executeUpdate();
		// entityManager.close();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @Override public PortalUser getUser(String username, String email) { // TODO
	 * Auto-generated method stub return null; }*
	 * 
	 * @see
	 * it.tsc.dao.UserDao#updateUser(java.lang.String,java.lang.String,java.lang.
	 * String,it.tsc.model. Role)
	 */
	public void updateUser(String username, String password, String email, Role role) {
		EntityManager entityManager = getEntityManager();
		TypedQuery<Users> query = entityManager.createNamedQuery(Users.UPDATE_USER, Users.class);
		query.setParameter("username", username);
		query.setParameter("password", password);
		query.setParameter("email", email);
		query.setParameter("role", role.toString());
		query.executeUpdate();
		// entityManager.close();
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
