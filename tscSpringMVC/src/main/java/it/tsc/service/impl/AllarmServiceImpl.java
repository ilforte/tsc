/**
 * 
 */
package it.tsc.service.impl;

import java.sql.Timestamp;

import org.springframework.stereotype.Service;

import it.tsc.service.AllarmService;

/**
 * @author astraservice
 *
 */
@Service("allarmService")
public class AllarmServiceImpl implements AllarmService {

  /**
   * 
   */
  public AllarmServiceImpl() {
    // TODO Auto-generated constructor stub
  }

  /*
   * (non-Javadoc)
   * 
   * @see it.tsc.service.AllamService#insertAllarmeMatricola(java.lang.String, java.sql.Timestamp,
   * java.lang.String, java.lang.String)
   */
  @Override
  public void insertAllarmeMatricola(String matricola, Timestamp data_arrivo, String evento,
      String serial_uuid) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see it.tsc.service.AllamService#insertAllarmeTel(java.lang.String, java.sql.Timestamp,
   * java.lang.String, java.lang.String)
   */
  @Override
  public void insertAllarmeTel(String tel, Timestamp data_arrivo, String evento,
      String serial_uuid) {
    // TODO Auto-generated method stub

  }

}
