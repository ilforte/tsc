/**
 * 
 */
package it.tsc.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import it.tsc.dao.BaseDao;
import it.tsc.dao.TscDao;
import it.tsc.domain.Allarm;
import it.tsc.domain.Anagrafica;
import it.tsc.util.JsonUtil;

/**
 * @author astraservice
 *
 */
@Repository("tscDao")
public class TscDaoImpl extends BaseDao implements TscDao {
	private static Logger logger = LoggerFactory.getLogger(TscDaoImpl.class);

	/**
	 * 
	 */
	public TscDaoImpl() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.tsc.dao.TscDao#getAnagrafica(it.tsc.model.Allarm)
	 */
	public String getAnagrafica(Allarm allarm) {
		if (allarm.getAb_codi() == null) {
			throw new IllegalArgumentException("ab_codi is empty");
		}
		EntityManager entityManager = getEntityManager();
		TypedQuery<Anagrafica> query = entityManager.createNamedQuery(Anagrafica.SELECT_ALL_ANAGRAFICA,
				Anagrafica.class);
		query.setParameter("ab_codi", allarm.getAb_codi());
		List<Anagrafica> list = query.getResultList();
		// entityManager.close();

		String result = JsonUtil.getGsonConverter().toJson(list);
		logger.debug("getAnagrafica {}", result);
		return result;

		// String sql =
		// "SELECT JSON ab_codi,nominativo,centrale,sesso FROM ks_tsc.tb_anagrafica
		// WHERE ab_codi = :ab_codi ALLOW FILTERING;";
		// PreparedStatement preparedStmt = baseDao.prepareAndCacheStatement(sql);
		// BoundStatement bound = preparedStmt.bind().setString("ab_codi",
		// allarm.getAb_codi());
		// ResultSet resultSet = baseDao.getSession().execute(bound);
	}

}
