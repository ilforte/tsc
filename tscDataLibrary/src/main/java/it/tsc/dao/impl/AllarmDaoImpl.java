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

import it.tsc.dao.AllarmDao;
import it.tsc.dao.BaseDao;
import it.tsc.domain.Allarm;
import it.tsc.util.JsonUtil;

/**
 * @author astraservice
 *
 */
@Repository("allarmDao")
public class AllarmDaoImpl extends BaseDao implements AllarmDao {
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
	 * @see it.tsc.dao.AllarmDao#insertAllarmeMatricola(java.lang.String,
	 * java.sql.Timestamp, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void insertAllarmeMatricola(String matricola, String ab_codi, Instant data_arrivo, String evento,
			String serial_uuid, String user) {
		Allarm allarm = new Allarm();
		allarm.setMatricola(matricola);
		allarm.setAb_codi(ab_codi);
		allarm.setData_arrivo(Date.from(data_arrivo));
		allarm.setEvento(evento);
		allarm.setSerial_uuid(serial_uuid);
		allarm.setUser(user);

		EntityManager entityManager = getEntityManager();
		entityManager.persist(allarm);
		// entityManager.close();

		// AllarmAccessor allarmAccessor = baseDao.createAccessor(AllarmAccessor.class);
		// allarmAccessor.insertAllarmeMatricola(matricola, ab_codi, data_arrivo,
		// evento, serial_uuid,
		// user);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.tsc.dao.AllarmDao#insertAllarmeTel(java.lang.String,
	 * java.sql.Timestamp, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void insertAllarmeTel(String tel, String ab_codi, Instant data_arrivo, String evento, String serial_uuid,
			String user) {
		Allarm allarm = new Allarm();
		allarm.setTel(tel);
		allarm.setAb_codi(ab_codi);
		allarm.setData_arrivo(Date.from(data_arrivo));
		allarm.setEvento(evento);
		allarm.setSerial_uuid(serial_uuid);
		allarm.setUser(user);

		EntityManager entityManager = getEntityManager();
		entityManager.persist(allarm);
		entityManager.flush();
		// entityManager.close();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.tsc.dao.AllarmDao#removeAllarme(java.lang.String)
	 */
	@Override
	public void removeAllarme(String serial_uuid) {
		Allarm allarm = new Allarm();
		allarm.setSerial_uuid(serial_uuid);

		EntityManager entityManager = getEntityManager();
		entityManager.remove(allarm);
		// entityManager.close();

		// AllarmAccessor allarmAccessor = baseDao.createAccessor(AllarmAccessor.class);
		// allarmAccessor.removeAllarme(serial_uuid);
	}

	@Override
	public void updateAllarme(String serial_uuid, String user) {
		EntityManager entityManager = getEntityManager();
		TypedQuery<Allarm> query = entityManager.createNamedQuery(Allarm.UPDATE_ALLARM, Allarm.class);
		query.setParameter("serial_uuid", serial_uuid);
		query.setParameter("user", user);
		query.executeUpdate();
		logger.debug("updateAllarme: {}", serial_uuid);
	}

	@Override
	public String jsonGetAllarms() {
		EntityManager entityManager = getEntityManager();
		TypedQuery<Allarm> query = entityManager.createNamedQuery(Allarm.SELECT_ALL_ALLARMS, Allarm.class);
		List<Allarm> list = query.getResultList();

		String result = JsonUtil.getGsonConverter().toJson(list);
		return result;

		// String sql =
		// "SELECT JSON matricola,ab_codi,data_arrivo,evento,user,serial_uuid FROM
		// ks_tsc.tb_allarms";
		// ResultSet resultSet = baseDao.getSession().execute(sql);
	}

}
