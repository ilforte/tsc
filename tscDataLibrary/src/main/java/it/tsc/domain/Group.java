/**
 * 
 */
package it.tsc.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

import it.tsc.domain.key.CompoundKey;

/**
 * @author astraservice
 *
 */
@Entity
@Table(name = "tb_groups", schema = "ks_tsc@cassandra_pu")
@NamedQueries(value = { @NamedQuery(name = Users.SELECT_ALL_USERS, query = "SELECT u FROM Users u"),
		@NamedQuery(name = Group.SELECT_GROUPS, query = "SELECT g FROM Group g"),
		@NamedQuery(name = Group.SELECT_GROUPS_BY_KEY, query = "SELECT g FROM Group g WHERE g.key = :key") })
public class Group {
	public static final String SELECT_GROUPS = "select.groups";
	public static final String SELECT_GROUPS_BY_KEY = "select.groups.by_key";
	@EmbeddedId
	@Expose
	private CompoundKey key = new CompoundKey();

	@Column(name = "groupname")
	@Expose
	private String groupName;

	public Group(CompoundKey key, String groupName) {
		super();
		this.key = key;
		this.groupName = groupName;
	}

	public CompoundKey getKey() {
		return key;
	}

	public void setKey(CompoundKey key) {
		this.key = key;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	/**
	 * 
	 */
	public Group() {
		super();
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
