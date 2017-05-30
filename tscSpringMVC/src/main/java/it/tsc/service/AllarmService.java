/**
 * 
 */
package it.tsc.service;

import java.sql.Timestamp;

/**
 * @author astraservice Manage Allarm in TSC
 */
public interface AllarmService {
  /**
   * Inserisce allarme per matricola (ITALTEL)
   * 
   * @param matricola
   * @param data_arrivo
   * @param evento
   * @param serial_uuid
   */

  public void insertAllarmeMatricola(String matricola, Timestamp data_arrivo, String evento,
      String serial_uuid);

  /**
   * Inserisce allarme per telefono (BRONDI)
   * 
   * @param tel
   * @param data_arrivo
   * @param evento
   * @param serial_uuid
   */
  public void insertAllarmeTel(String tel, Timestamp data_arrivo, String evento,
      String serial_uuid);

}

