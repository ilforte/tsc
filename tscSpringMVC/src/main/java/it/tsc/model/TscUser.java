/**
 * 
 */
package it.tsc.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.google.gson.annotations.Expose;

/**
 * @author astraservice POJO TSC User
 */
public class TscUser {
  @Expose
  private String username;
  @Expose
  private List<String> roles;
  @Expose
  private String email;

  /**
   * 
   */
  public TscUser() {

  }

  public TscUser(ApplicationUser user) {
    super();
    if (user != null) {
      this.username = user.getUsername();
      this.email = user.getEmail();
      roles = new ArrayList<String>();
      for (GrantedAuthority ga : user.getAuthorities()) {
        roles.add(ga.getAuthority());
      }
    }
  }

  public TscUser(String username, List<String> roles, String email) {
    super();
    this.username = username;
    this.roles = roles;
    this.email = email;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public List<String> getRoles() {
    return roles;
  }

  public void setRoles(List<String> roles) {
    this.roles = roles;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

}
