/**
 * 
 */
package it.tsc.dao.impl;

import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.datastax.driver.core.ResultSet;

import it.tsc.dao.AllarmDao;
import it.tsc.dao.BaseDao;
import it.tsc.dao.accessor.AllarmAccessor;
import it.tsc.util.ConversionUtil;

/**
 * @author astraservice
 *
 */
@Repository("allarmDao")
public class AllarmDaoImpl implements AllarmDao {
  @SuppressWarnings("unused")
  private static Logger logger = LoggerFactory.getLogger(AllarmDaoImpl.class);
  @Autowired
  private BaseDao baseDao;

  /**
   * 
   */
  public AllarmDaoImpl() {
    // TODO Auto-generated constructor stub
  }

  /*
   * (non-Javadoc)
   * 
   * @see it.tsc.dao.AllarmDao#insertAllarmeMatricola(java.lang.String, java.sql.Timestamp,
   * java.lang.String, java.lang.String, java.lang.String)
   */
  @Override
  public void insertAllarmeMatricola(String matricola, String ab_codi, Instant data_arrivo,
      String evento, String serial_uuid, String user) {
    AllarmAccessor allarmAccessor = baseDao.createAccessor(AllarmAccessor.class);
    allarmAccessor.insertAllarmeMatricola(matricola, ab_codi, data_arrivo, evento, serial_uuid,
        user);
  }

  /*
   * (non-Javadoc)
   * 
   * @see it.tsc.dao.AllarmDao#insertAllarmeTel(java.lang.String, java.sql.Timestamp,
   * java.lang.String, java.lang.String, java.lang.String)
   */
  @Override
  public void insertAllarmeTel(String tel, String ab_codi, String data_arrivo, String evento,
      String serial_uuid, String user) {

  }

  /*
   * (non-Javadoc)
   * 
   * @see it.tsc.dao.AllarmDao#removeAllarme(java.lang.String)
   */
  @Override
  public void removeAllarme(String serial_uuid) {
    AllarmAccessor allarmAccessor = baseDao.createAccessor(AllarmAccessor.class);
    allarmAccessor.removeAllarme(serial_uuid);
  }

  @Override
  public void updateAllarme(String serial_uuid, String user) {
    AllarmAccessor allarmAccessor = baseDao.createAccessor(AllarmAccessor.class);
    allarmAccessor.updateAllarme(serial_uuid, user);
  }

  @Override
  public String jsonGetAllarms() {
    String sql =
        "SELECT JSON matricola,ab_codi,data_arrivo,evento,user,serial_uuid FROM ks_tsc.tb_allarms";
    ResultSet resultSet = baseDao.getSession().execute(sql);
    String result = ConversionUtil.returnJson(resultSet.all());
    return result;
  }

}
