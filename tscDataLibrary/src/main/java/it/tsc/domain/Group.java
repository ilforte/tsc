/**
 * 
 */
package it.tsc.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

import it.tsc.domain.key.CompoundKey;

/**
 * @author astraservice
 *
 */
@Entity
@Table(name = "tb_groups", schema = "ks_tsc@cassandra_pu")
public class Group {
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
