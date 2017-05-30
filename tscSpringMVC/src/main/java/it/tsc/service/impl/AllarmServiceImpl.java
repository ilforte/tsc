/**
 * 
 */
package it.tsc.service.impl;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.tsc.dao.AllarmDao;
import it.tsc.service.AllarmService;

/**
 * @author astraservice
 *
 */
@Service("allarmService")
public class AllarmServiceImpl implements AllarmService {
  @Autowired
  private AllarmDao allarmDao;

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
  public void insertAllarmeMatricola(String matricola, String ab_codi, Timestamp data_arrivo,
      String evento, String serial_uuid, String user) {
    allarmDao.insertAllarmeMatricola(matricola, ab_codi, data_arrivo, evento, serial_uuid, user);
  }

  /*
   * (non-Javadoc)
   * 
   * @see it.tsc.service.AllamService#insertAllarmeTel(java.lang.String, java.sql.Timestamp,
   * java.lang.String, java.lang.String)
   */
  @Override
  public void insertAllarmeTel(String tel, String ab_codi, Timestamp data_arrivo, String evento,
      String serial_uuid, String user) {


  }

  @Override
  public void removeAllarme(String serial_uuid) {
    allarmDao.removeAllarme(serial_uuid);
  }

  @Override
  public String jsonGetAllarms() {
    return allarmDao.jsonGetAllarms();
  }

}
