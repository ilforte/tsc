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
 * @author astraservice
 *
 */
@Entity
@Table(name = "tb_groups", schema = "ks_tsc@cassandra_pu")
@NamedQueries(value = { @NamedQuery(name = Group.SELECT_GROUPS, query = "SELECT g FROM Group g"),
		@NamedQuery(name = Group.SELECT_GROUPS_BY_KEY, query = "SELECT g FROM Group g WHERE g.key = :key") })
public class Group {
	public static final String SELECT_GROUPS = "select.groups";
	public static final String SELECT_GROUPS_BY_KEY = "select.groups.by_key";

	@Id
	@Column(name = "groupid")
	private String groupid;

	@Column(name = "username")
	@Expose
	private String username;

	@Column(name = "role")
	@Expose
	private String role;

	@Column(name = "groupname")
	@Expose
	private String groupName;

	/**
	 * 
	 */
	public Group() {
		super();
	}

	public Group(String groupid, String username, String role, String groupName) {
		super();
		this.groupid = groupid;
		this.username = username;
		this.role = role;
		this.groupName = groupName;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
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

	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Group) {
			return this.getGroupName().equals(((Group) obj).getGroupName());
		}
		return false;
	}

	@Override
	public int hashCode() {
		return getGroupName().hashCode();
	}

}
