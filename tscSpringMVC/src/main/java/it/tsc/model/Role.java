/**
 * 
 */
package it.tsc.model;

/**
 * @author astraservice
 *
 */
public enum Role {

  ROLE_ADMIN, ROLE_USER, ROLE_BACKOFFICE;

  /**
   * return String Value for role
   * 
   * @param role
   * @return
   */
  public String value(Role role) {
    switch (role) {
      case ROLE_ADMIN:
        return "ROLE_ADMIN";
      case ROLE_USER:
        return "ROLE_USER";
      case ROLE_BACKOFFICE:
        return "ROLE_BACKOFFICE";
      default:
        throw new RuntimeException("Role cannot be converted");
    }
  }

}
