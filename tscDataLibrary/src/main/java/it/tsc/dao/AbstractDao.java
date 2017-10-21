/**
 * 
 */
package it.tsc.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author astraservice
 *
 */
public abstract class AbstractDao {
  @Autowired
  protected EntityManagerFactory entityManagerFactory;

  /**
   * 
   */
  public AbstractDao() {

  }

}
