/**
 * 
 */
package it.tsc.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * @author astraservice
 *
 */
@WebService
public class TscWebService {
  @WebMethod(operationName = "insertAllarmEuropeAssistance")
  public boolean insertAllarmEuropeAssistance(String tel, String ab_codi, String evento,
      String user) {
    return false;
  }
}
