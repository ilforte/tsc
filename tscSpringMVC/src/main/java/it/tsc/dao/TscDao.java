/**
 * 
 */
package it.tsc.dao;

import it.tsc.model.Allarm;
import it.tsc.model.Anagrafica;

/**
 * @author astraservice Dao for TSC Service
 */
public interface TscDao {

  /**
   * Obtain Anagrafica giving allarm
   * 
   * @param allarm
   * @return
   */
  public Anagrafica getAnagrafica(Allarm allarm);

}
