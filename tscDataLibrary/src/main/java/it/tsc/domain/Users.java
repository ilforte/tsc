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
 * tb_users
 */
@Entity
@Table(name = "tb_users", schema = "ks_tsc@cassandra_pu")
public class Users extends BaseDomain{
    /**
	 * 
	 */
	private static final long serialVersionUID = -5293616948185366680L;
	
	@EmbeddedId
	@Expose
	private CompoundKey key;
	@Column
	@Expose
	private String base32Secret;
	@Column
	@Expose
	private String email;
	
	public CompoundKey getKey() {
		return key;
	}

	public void setKey(CompoundKey key) {
		this.key = key;
	}


}
