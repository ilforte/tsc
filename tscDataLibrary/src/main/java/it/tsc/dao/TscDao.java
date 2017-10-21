/**
 * 
 */
package it.tsc.dao;

import it.tsc.domain.Allarm;

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
  public String getAnagrafica(Allarm allarm);

}
