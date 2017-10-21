/**
 * 
 */
package it.tsc.domain;

/**
 * @author astraservice
 *
 */
public enum Response {

  SUCCESS, FAILURE;

  /**
   * return String Value for role
   * 
   * @param role
   * @return
   */
  public String value(Response response) {
    switch (response) {
      case SUCCESS:
        return "SUCCESS";
      case FAILURE:
        return "FAILURE";
      default:
        throw new RuntimeException("Response cannot be converted");
    }
  }

}
