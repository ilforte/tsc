/**
 * 
 */
package it.tsc.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.google.gson.annotations.Expose;

/**
 * @author astraservice
 */
public class ApplicationUser extends User {
  private static final long serialVersionUID = 4389964117627782934L;
  @Expose
  private String email;
  @Expose
  private String username;

  /**
   * User Bean
   */
  public ApplicationUser(String username, String password, boolean enabled,
      boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked,
      Collection<GrantedAuthority> authorities, String email) {
    super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked,
        authorities);
    this.username = username;
    this.email = email;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public Collection<GrantedAuthority> getAuthorities() {
    return super.getAuthorities();
  }

  @Override
  public String getUsername() {
    return super.getUsername();
  }

  @Override
  public boolean isEnabled() {
    return super.isEnabled();
  }

  @Override
  public boolean isAccountNonExpired() {
    return super.isAccountNonExpired();
  }

  @Override
  public boolean isAccountNonLocked() {
    return super.isAccountNonLocked();
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return super.isCredentialsNonExpired();
  }

}
