/**
 * 
 */
package it.tsc.service;

import it.tsc.model.Allarm;

/**
 * @author astraservice TSC Service
 */
public interface TscService {

  /**
   * Obtain Anagrafica giving allarm (JSON)
   * 
   * @param allarm
   * @return
   */
  public String getAnagrafica(Allarm allarm);

}
