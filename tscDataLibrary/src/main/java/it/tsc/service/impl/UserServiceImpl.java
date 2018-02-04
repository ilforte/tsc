/**
 * 
 */
package it.tsc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import it.tsc.dao.UserDao;
import it.tsc.domain.ApplicationUser;
import it.tsc.domain.PortalUser;
import it.tsc.domain.Role;
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
	@Override
	public PortalUser getUser(String username) {
		return userDao.getUser(username);
	}

	@Override
	public PortalUser getUser(String username, String email) {
		return userDao.getUser(username, email);
	}

	@Override
	public String jsonGetUser(String username) {
		return userDao.jsonGetUser(username);
	}

	@Override
	public List<PortalUser> getAllUsers() {
		return userDao.getAllUsers();
	}

	@Override
	public String jsonGetAllUsers() {
		return userDao.jsonGetAllUsers();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.tsc.service.UserService#getUserRoles(java.lang.String,java.lang.String)
	 */
	@Override
	public List<GrantedAuthority> getUserRoles(String username) {
		return userDao.getUserRoles(username);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.tsc.service.UserService#isAdmin(it.tsc.model.Role)
	 */
	@SuppressWarnings("unlikely-arg-type")
	public boolean isAdmin(ApplicationUser requester) {
		return requester.getAuthorities() != null && requester.getAuthorities().contains(Role.ROLE_ADMIN) ? true
				: false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.tsc.service.UserService#addUser(java.lang.String, java.lang.String,
	 * it.tsc.model.Role, it.tsc.model.User)
	 */
	@Override
	public boolean addUser(String username, String password, String email, Role role, boolean mfaEnabled) {
		return userDao.addUser(username, password, email, role, mfaEnabled);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.tsc.service.UserService#removeUser(java.lang.String,
	 * java.lang.String, it.tsc.model.Role, it.tsc.model.User)
	 */
	@Override
	public boolean removeUser(String username, Role role) {
		return userDao.removeUser(username, role);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.tsc.service.UserService#updateUser(java.lang.String,
	 * it.tsc.model.Role, it.tsc.model.User)
	 */
	@Override
	public void updateUser(String username, String password, String email, Role role, boolean mfaEnabled) {
		userDao.updateUser(username, password, email, role, mfaEnabled);
	}

	@Override
	public boolean isAdmin(Role role) {
		return role.equals(Role.ROLE_ADMIN);
	}

	@Override
	public boolean isAdmin(PortalUser user) {
		if (getUser(user.getUsername()) == null) {
			return false;
		} else if (getUser(user.getUsername()).getRoles() != null) {
			return getUser(user.getUsername()).getRoles().contains(Role.ROLE_ADMIN.toString());
		} else {
			return false;
		}

	}

	@Override
	public boolean isSuperAdmin(PortalUser user) {
		if (getUser(user.getUsername()) != null && getUser(user.getUsername()).getRoles() != null) {
			return getUser(user.getUsername()).getRoles().contains(Role.ROLE_SADMIN.toString());
		} else {
			return false;
		}

	}

	@Override
	public void updateMfaUserKey(String username, String keyId, String base32Secret) {
		/**
		 * iterate all over user role
		 */
		if (userDao != null && userDao.getUserRoles(username) != null) {
			for (GrantedAuthority grantedAuthority : userDao.getUserRoles(username)) {
				userDao.updateMfaUserKey(username, keyId, base32Secret, grantedAuthority.getAuthority());
			}
		}
	}

}
