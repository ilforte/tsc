/**
 * 
 */
package it.tsc.service;

import it.tsc.model.Allarm;
import it.tsc.model.Anagrafica;

/**
 * @author astraservice TSC Service
 */
public interface TscService {

  /**
   * Obtain Anagrafica giving allarm
   * 
   * @param allarm
   * @return
   */
  public Anagrafica getAnagrafica(Allarm allarm);

}
