/**
 * 
 */
package it.tsc.dao.impl;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.datastax.driver.core.ResultSet;

import it.tsc.dao.AbstractDao;
import it.tsc.dao.AllarmDao;
import it.tsc.dao.accessor.AllarmAccessor;
import it.tsc.domain.Allarm;
import it.tsc.util.ConversionUtil;

/**
 * @author astraservice
 *
 */
@Repository("allarmDao")
public class AllarmDaoImpl extends AbstractDao implements AllarmDao {
  @SuppressWarnings("unused")
  private static Logger logger = LoggerFactory.getLogger(AllarmDaoImpl.class);
  

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
  public void insertAllarmeMatricola(String matricola, String ab_codi, Instant data_arrivo,
      String evento, String serial_uuid, String user) {
	  Allarm allarm = new Allarm();
	  allarm.setMatricola(matricola);
	  allarm.setAb_codi(ab_codi);
	  allarm.setData_arrivo(Date.from(data_arrivo));
	  allarm.setEvento(evento);
	  allarm.setSerial_uuid(serial_uuid);
	  allarm.setUser(user);
	  
      EntityManager entityManager = entityManagerFactory.createEntityManager();
      entityManager.persist(allarm);
      entityManager.close();
	  
//    AllarmAccessor allarmAccessor = baseDao.createAccessor(AllarmAccessor.class);
//    allarmAccessor.insertAllarmeMatricola(matricola, ab_codi, data_arrivo, evento, serial_uuid,
//        user);
  }

  /*
   * (non-Javadoc)
   * 
   * @see it.tsc.dao.AllarmDao#insertAllarmeTel(java.lang.String, java.sql.Timestamp,
   * java.lang.String, java.lang.String, java.lang.String)
   */
  public void insertAllarmeTel(String tel, String ab_codi, Instant data_arrivo, String evento,
      String serial_uuid, String user) {
	  Allarm allarm = new Allarm();
	  allarm.setTel(tel);
	  allarm.setAb_codi(ab_codi);
	  allarm.setData_arrivo(Date.from(data_arrivo));
	  allarm.setEvento(evento);
	  allarm.setSerial_uuid(serial_uuid);
	  allarm.setUser(user);
	  
      EntityManager entityManager = entityManagerFactory.createEntityManager();
      entityManager.persist(allarm);
      entityManager.close();
  }

  /*
   * (non-Javadoc)
   * 
   * @see it.tsc.dao.AllarmDao#removeAllarme(java.lang.String)
   */
  public void removeAllarme(String serial_uuid) {
	Allarm allarm = new Allarm();
	allarm.setSerial_uuid(serial_uuid);
	
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.remove(allarm);
    entityManager.close();
	
//    AllarmAccessor allarmAccessor = baseDao.createAccessor(AllarmAccessor.class);
//    allarmAccessor.removeAllarme(serial_uuid);
  }

  public void updateAllarme(String serial_uuid, String user) {
	Allarm allarm = new Allarm();
	allarm.setSerial_uuid(serial_uuid);
	allarm.setUser(user);
	
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.merge(allarm);
    entityManager.flush();
    entityManager.close();
//    AllarmAccessor allarmAccessor = baseDao.createAccessor(AllarmAccessor.class);
//    allarmAccessor.updateAllarme(serial_uuid, user);
  }

  public String jsonGetAllarms() {
	    EntityManager entityManager = entityManagerFactory.createEntityManager();
	    TypedQuery<Allarm> query = entityManager.createNamedQuery(Allarm.SELECT_ALL_ALLARMS, Allarm.class);
	    List<Allarm> list = query.getResultList();
	    entityManager.close();
	  
	    String result = ConversionUtil.getGsonConverter().toJson(list);
        return result;
    
//    String sql =
//        "SELECT JSON matricola,ab_codi,data_arrivo,evento,user,serial_uuid FROM ks_tsc.tb_allarms";
//    ResultSet resultSet = baseDao.getSession().execute(sql);
  }

}
