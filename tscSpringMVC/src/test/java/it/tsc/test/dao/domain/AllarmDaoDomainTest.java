/**
 * 
 */
package it.tsc.test.dao.domain;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import it.tsc.domain.Allarm;
import it.tsc.test.dao.BaseDaoTest;

/**
 * @author astraservice
 *
 */
public class AllarmDaoDomainTest extends BaseDaoTest{
  @Autowired
  private EntityManagerFactory entityManagerFactory;
  
  @Test
  public void allarmsTest() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TypedQuery<Allarm> findQuery = entityManager.createQuery("Select a from Allarm a", Allarm.class);
        List<Allarm> allAllarms = findQuery.getResultList();
        assertNotEquals(0L, allAllarms.size());
        assertNotNull(entityManager);
  }

}
