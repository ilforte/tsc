/**
 * 
 */
package it.tsc.domain.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.junit.Test;

import com.impetus.client.cassandra.common.CassandraConstants;

import it.tsc.domain.Allarm;
import it.tsc.domain.Users;

/**
 * @author astraservice
 *
 */
public class DomainTest {
	private final String _PU = "cassandra_pu";
	
	@Test
	public void allarmsTest() {
        Map<String, String> props = new HashMap<String, String>();
        props.put(CassandraConstants.CQL_VERSION, CassandraConstants.CQL_VERSION_3_0);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(_PU, props);
        EntityManager em = emf.createEntityManager();  
        TypedQuery<Allarm> findQuery = em.createQuery("Select a from Allarm a", Allarm.class);
        List<Allarm> allAllarms = findQuery.getResultList();
        assertNotEquals(0L, allAllarms.size());
        assertNotNull(em);
	}
	
	@Test
	public void userTest() {
        Map<String, String> props = new HashMap<String, String>();
        props.put(CassandraConstants.CQL_VERSION, CassandraConstants.CQL_VERSION_3_0);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(_PU, props);
        EntityManager em = emf.createEntityManager();  
        TypedQuery<Users> findQuery = em.createQuery("Select u from Users u where u.key.username = :username", Users.class);
        findQuery.setParameter("username", "admin");
		List<Users> allUsers = findQuery.getResultList();
        assertEquals(1L, allUsers.size());
        assertNotNull(em);
	}
	
	@Test
	public void userTestAndRole() {
        Map<String, String> props = new HashMap<String, String>();
        props.put(CassandraConstants.CQL_VERSION, CassandraConstants.CQL_VERSION_3_0);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(_PU, props);
        EntityManager em = emf.createEntityManager();  
        TypedQuery<Users> findQuery = em.createQuery("Select u from Users u where u.key.username = :username and u.key.role = :role", Users.class);
        findQuery.setParameter("username", "admin");
        findQuery.setParameter("role", "ROLE_SADMIN");
        List<Users> allUsers = findQuery.getResultList();
        assertEquals(1L, allUsers.size());
        assertNotNull(em);
	}


}
