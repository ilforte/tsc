/**
 * 
 */
package it.tsc.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

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

	public CompoundKey getKey() {
		return key;
	}

	public void setKey(CompoundKey key) {
		this.key = key;
	}

	@EmbeddedId
    private CompoundKey key;

}
