/**
 * 
 */
package it.tsc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.tsc.dao.TscDao;
import it.tsc.model.Allarm;
import it.tsc.service.TscService;

/**
 * @author astraservice
 *
 */
@Service("tscService")
public class TscServiceImpl implements TscService {
  @Autowired
  private TscDao tscDao;

  /**
   * 
   */
  public TscServiceImpl() {
    // TODO Auto-generated constructor stub
  }

  /*
   * (non-Javadoc)
   * 
   * @see it.tsc.service.TscService#getAnagrafica(it.tsc.model.Allarm)
   */
  @Override
  public String getAnagrafica(Allarm allarm) {
    return tscDao.getAnagrafica(allarm);
  }

}
