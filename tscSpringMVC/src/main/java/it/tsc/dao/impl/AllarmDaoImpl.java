/**
 * 
 */
package it.tsc.dao.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.mapping.MappingManager;

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
  public void insertAllarmeMatricola(String matricola, String ab_codi, Date data_arrivo,
      String evento, String serial_uuid, String user) {
    AllarmAccessor allarmAccessor = createAccessor(AllarmAccessor.class);
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
  public void insertAllarmeTel(String tel, String ab_codi, Date data_arrivo, String evento,
      String serial_uuid, String user) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see it.tsc.dao.AllarmDao#removeAllarme(java.lang.String)
   */
  @Override
  public void removeAllarme(String serial_uuid) {
    AllarmAccessor allarmAccessor = createAccessor(AllarmAccessor.class);
    allarmAccessor.removeAllarme(serial_uuid);
  }

  @Override
  public void updateAllarme(String serial_uuid, String user) {
    AllarmAccessor allarmAccessor = createAccessor(AllarmAccessor.class);
    allarmAccessor.updateAllarme(serial_uuid, user);
  }

  /**
   * Return properly groupAccessor
   * 
   * @param <T>
   * 
   * @param <T>
   * @return
   * 
   * @return
   */
  public <T> T createAccessor(Class<T> klass) {
    MappingManager manager = baseDao.getMappingManager();
    return manager.createAccessor(klass);
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
