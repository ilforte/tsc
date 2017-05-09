/**
 * 
 */
package it.tsc.dao;

import org.springframework.beans.factory.annotation.Autowired;

import com.datastax.driver.core.Session;

/**
 * @author astraservice AbstractDataAccess for cassandra
 */
public abstract class AbstractDataAccess {

  @Autowired
  protected GenericDao genericDao;

  /**
   * 
   */
  public AbstractDataAccess() {
    // TODO Auto-generated constructor stub
  }

  /**
   * Provide my Session.
   *
   * @return My session.
   */
  public Session getSession() {
    return genericDao.getSession();
  }

  /** Close cluster. */
  public void close() {
    genericDao.close();
  }

}
