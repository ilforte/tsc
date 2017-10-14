/**
 * 
 */
package it.tsc.domain.key;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author astraservice
 *
 */
@Embeddable
public class CompoundKey {
	
	@Column(name="username") 
    private String username;
	@Column(name="role")  
    private String role;
    
	public CompoundKey() {
		super();
		// TODO Auto-generated constructor stub
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
