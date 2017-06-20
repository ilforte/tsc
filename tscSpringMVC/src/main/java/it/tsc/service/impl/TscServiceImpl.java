/**
 * 
 */
package it.tsc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.tsc.dao.UserDao;
import it.tsc.model.Allarm;
import it.tsc.model.Anagrafica;
import it.tsc.service.TscService;

/**
 * @author astraservice
 *
 */
@Service("tscService")
public class TscServiceImpl implements TscService {
  @Autowired
  private UserDao userDao;

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
  public Anagrafica getAnagrafica(Allarm allarm) {
    // TODO Auto-generated method stub
    return null;
  }

}
