/**
 * 
 */
package it.tsc.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

/**
 * @author astraservice tb_users
 */
@Entity
@Table(name = "tb_users", schema = "ks_tsc@cassandra_pu")
@NamedQueries(value = { @NamedQuery(name = Users.SELECT_ALL_USERS, query = "SELECT u FROM Users u"),
		@NamedQuery(name = Users.SELECT_BY_USERNAME, query = "SELECT u FROM Users u WHERE u.username = :username"),
		@NamedQuery(name = Users.SELECT_BY_USERNAME_ROLE, query = "SELECT u FROM Users u WHERE u.username = :username AND u.role=:role"),
		@NamedQuery(name = Users.SELECT_BY_USERNAME_EMAIL, query = "SELECT u FROM Users u WHERE u.username = :username AND u.email=:email"),
		@NamedQuery(name = Users.UPDATE_BY_USERNAME_ROLE, query = "UPDATE Users u SET u.keyId=:keyId,u.base32Secret=:base32Secret WHERE u.username = :username AND u.role=:role"),
		@NamedQuery(name = Users.UPDATE_USER, query = "UPDATE Users u SET u.password=:password,u.email=:email WHERE u.username = :username AND u.role = :role") })
public class Users extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5293616948185366680L;
	public static final String SELECT_ALL_USERS = "select.users";
	public static final String SELECT_BY_USERNAME = "select.by.username";
	public static final String SELECT_BY_USERNAME_ROLE = "select.by.username.role";
	public static final String SELECT_BY_USERNAME_EMAIL = "select.by.username.email";
	public static final String UPDATE_BY_USERNAME_ROLE = "update.by.username.role";
	public static final String UPDATE_USER = "update.user";

	@Id
	@Column(name = "userid")
	private String userid;

	@Column(name = "username")
	@Expose
	private String username;

	@Column(name = "role")
	@Expose
	private String role;

	@Column
	private String password;

	@Column(name = "base32secret")
	@Expose
	private String base32Secret;

	@Column(name = "mfaenabled")
	@Expose
	private boolean mfaEnabled;

	@Column
	@Expose
	private String email;

	@Column(name = "keyid")
	@Expose
	private String keyId;

	// @OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	// @JoinColumns({ @JoinColumn(name = "username", columnDefinition = "username"),
	// @JoinColumn(name = "role", columnDefinition = "role") })
	// private List<Group> groups = new ArrayList<Group>();

	/**
	 * 
	 */
	public Users() {
		super();
	}

	/**
	 * base constructor
	 * 
	 * @param username
	 * @param role
	 */
	public Users(String username, String role) {
		super();
		this.username = username;
		this.role = role;
	}

	/**
	 * base constructor
	 * 
	 * @param username
	 * @param role
	 */
	public Users(String username, Role role) {
		super();
		this.username = username;
		this.role = role.toString();
	}

	/**
	 * 
	 * @param userid
	 * @param username
	 * @param role
	 * @param mfaEnabled
	 */
	public Users(String userid, String username, String role, boolean mfaEnabled) {
		super();
		this.userid = userid;
		this.username = username;
		this.role = role;
		this.mfaEnabled = mfaEnabled;
	}

	/**
	 * 
	 * @param userid
	 * @param username
	 * @param password
	 * @param role
	 * @param email
	 */
	public Users(String userid, String username, String password, Role role, String email) {
		super();
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.role = role.toString();
		this.email = email;
	}

	/**
	 * 
	 * @param username
	 * @param role
	 * @param base32Secret
	 * @param keyId
	 */
	public Users(String username, String role, String base32Secret, String keyId) {
		super();
		this.username = username;
		this.role = role;
		this.base32Secret = base32Secret;
		this.keyId = keyId;
	}

	/**
	 * 
	 * @param userid
	 * @param username
	 * @param role
	 * @param mfaEnabled
	 */
	public Users(String userid, String username, Role role, boolean mfaEnabled) {
		super();
		this.userid = userid;
		this.username = username;
		this.role = role.toString();
		this.mfaEnabled = mfaEnabled;
	}

	// public List<Group> getGroups() {
	// return groups;
	// }
	//
	// public void setGroups(List<Group> groups) {
	// this.groups = groups;
	// }

	public String getBase32Secret() {
		return base32Secret;
	}

	public void setBase32Secret(String base32Secret) {
		this.base32Secret = base32Secret;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getKeyId() {
		return keyId;
	}

	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isMfaEnabled() {
		return mfaEnabled;
	}

	public void setMfaEnabled(boolean mfaEnabled) {
		this.mfaEnabled = mfaEnabled;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
