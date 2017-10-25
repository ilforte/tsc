/**
 * 
 */
package it.tsc.test.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import it.tsc.domain.Anagrafica;

/**
 * @author astraservice
 *
 */
public class GenericDaoTest extends BaseDaoTest {
  // @PersistenceContext(name = "sharedEntityManager")
  // private EntityManager entityManager;
  @Autowired
  private EntityManagerFactory entityManagerFactory;
  private EntityManager entityManager;

  @Test
  public void testGenericDao() {
    getEntityManager();
    assertNotNull(entityManager);
    assertTrue(entityManager.isOpen());
    Anagrafica anagrafica = new Anagrafica();
    anagrafica.setNominativo("matteo");
    anagrafica.setAb_codi("0000");
    entityManager.persist(anagrafica);
    // entityManager.flush();

    TypedQuery<Anagrafica> query =
        entityManager.createQuery("select a from Anagrafica a", Anagrafica.class);
    assertTrue(query.getResultList().size() == 1);
    entityManager.remove(anagrafica);
    query = entityManager.createQuery("select a from Anagrafica a", Anagrafica.class);
    assertTrue(query.getResultList().size() == 0);
  }

  public EntityManager getEntityManager() {
    if (entityManagerFactory != null) {
      if (entityManager == null) {
        entityManager = entityManagerFactory.createEntityManager();
        return entityManager;
      } else if (!entityManager.isOpen()) {
        entityManager = entityManagerFactory.createEntityManager();
        return entityManager;
      } else {
        return entityManager;
      }
    } else {
      throw new RuntimeException("entityManagerFactory cannotbe null");
    }
  }



}
