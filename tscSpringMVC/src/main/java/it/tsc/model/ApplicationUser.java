/**
 * 
 */
package it.tsc.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * @author astraservice
 */
public class ApplicationUser extends User {
  private static final long serialVersionUID = 4389964117627782934L;
  private String email;
  private String username;
  private String password;

  /**
   * User Bean
   */
  public ApplicationUser(String username, String password, boolean enabled,
      boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked,
      Collection<GrantedAuthority> authorities, String email) {
    super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked,
        authorities);
    this.email = email;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

}
