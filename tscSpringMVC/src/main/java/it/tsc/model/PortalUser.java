/**
 * 
 */
package it.tsc.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.groups.Default;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import com.datastax.driver.mapping.annotations.Transient;
import com.google.gson.annotations.Expose;

/**
 * @author astraservice POJO TSC User
 */
@Table(keyspace = "ks_tsc", name = "tb_users", readConsistency = "QUORUM",
    writeConsistency = "QUORUM", caseSensitiveKeyspace = false, caseSensitiveTable = false)
public class PortalUser {
  @NotBlank(groups = {PortalUserRemove.class, PortalUserRenewPassword.class})
  @Expose
  @PartitionKey(0)
  @Column(name = "username")
  private String username;

  @NotBlank(groups = {PortalUserRemove.class})
  @Expose
  @PartitionKey(1)
  @Column(name = "role")
  private String role;

  @Transient
  @Expose
  private List<String> roles;

  @NotBlank(groups = {PortalUserInsert.class, PortalUserRenewPassword.class})
  @Email
  @Expose
  @Column(name = "email")
  private String email;

  @NotBlank(groups = PortalUserInsert.class)
  @Expose
  @Column(name = "password")
  private String password;

  /**
   * 
   */
  public PortalUser() {

  }

  public PortalUser(ApplicationUser user) {
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

  public PortalUser(String username, List<String> roles, String email) {
    super();
    this.username = username;
    this.roles = roles;
    this.email = email;
  }

  /**
   * 
   * @author astraservice
   *
   */
  public interface PortalUserInsertValidator {

  }

  /**
   * 
   * @author astraservice
   *
   */
  // @formatter:off
    public interface PortalUserRemove extends Default {}
    public interface PortalUserInsert extends PortalUserRemove {}
    public interface PortalUserRenewPassword extends Default {}
  // @formatter:on


  public interface Draft extends Default {
  }

  public interface Printing extends Draft {
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

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public PortalUser(String username, String password, List<String> roles, String email) {
    super();
    this.username = username;
    this.roles = roles;
    this.email = email;
    this.password = password;
  }

}
