/**
 * 
 */
package it.tsc.service;

import java.time.Instant;

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
   * @param user
   */
  public void insertAllarmeMatricola(String matricola, String ab_codi, Instant data_arrivo,
      String evento, String serial_uuid, String user);

  /**
   * Inserisce allarme per telefono (BRONDI)
   * 
   * @param tel
   * @param data_arrivo
   * @param evento
   * @param serial_uuid
   * @param user
   */
  public void insertAllarmeTel(String tel, String ab_codi, String data_arrivo, String evento,
      String serial_uuid, String user);

  /**
   * rimuove allarme
   * 
   * @param serial_uuid
   */
  public void removeAllarme(String serial_uuid);

  /**
   * update Allarme
   * 
   * @param serial_uuid
   * @param user
   */
  public void updateAllarme(String serial_uuid, String user);

  /**
   * get allarms in json format
   * 
   * @return
   */
  public String jsonGetAllarms();

}

