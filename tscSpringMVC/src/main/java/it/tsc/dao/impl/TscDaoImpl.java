/**
 * 
 */
package it.tsc.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import it.tsc.dao.BaseDao;
import it.tsc.dao.TscDao;
import it.tsc.model.Allarm;
import it.tsc.model.Anagrafica;

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
    // TODO Auto-generated constructor stub
  }

  /*
   * (non-Javadoc)
   * 
   * @see it.tsc.dao.TscDao#getAnagrafica(it.tsc.model.Allarm)
   */
  @Override
  public Anagrafica getAnagrafica(Allarm allarm) {
    // TODO Auto-generated method stub
    return null;
  }

}
