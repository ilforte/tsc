/**
 * 
 */
package it.tsc.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;

import it.tsc.dao.BaseDao;
import it.tsc.dao.TscDao;
import it.tsc.model.Allarm;
import it.tsc.util.ConversionUtil;

/**
 * @author astraservice
 *
 */
@Repository("tscDao")
public class TscDaoImpl implements TscDao {
  private static Logger logger = LoggerFactory.getLogger(TscDaoImpl.class);
  @Autowired
  private BaseDao baseDao;

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
  @Override
  public String getAnagrafica(Allarm allarm) {
    if (allarm.getAb_codi() == null) {
      throw new IllegalArgumentException("ab_codi is empty");
    }
    String sql =
        "SELECT JSON ab_codi,nominativo FROM ks_tsc.tb_anagrafica WHERE ab_codi = :ab_codi ALLOW FILTERING;";
    PreparedStatement preparedStmt = baseDao.prepareAndCacheStatement(sql);
    BoundStatement bound = preparedStmt.bind().setString("ab_codi", allarm.getAb_codi());
    ResultSet resultSet = baseDao.getSession().execute(bound);
    String result = ConversionUtil.returnJson(resultSet.all());
    logger.debug("getAnagrafica {}", result);
    return result;
  }

}
