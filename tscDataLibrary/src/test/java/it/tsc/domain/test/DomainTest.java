/**
 * 
 */
package it.tsc.domain.test;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.persistence.TypedQuery;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.code.tempusfugit.concurrency.ConcurrentTestRunner;

import it.tsc.domain.Allarm;
import it.tsc.domain.Users;

/**
 * @author astraservice
 *
 */
@RunWith(ConcurrentTestRunner.class)
public class DomainTest extends BaseDomainTest {

	@Test
	public void allarmsTest() {
		TypedQuery<Allarm> findQuery = getEntityManager().createQuery("Select a from Allarm a", Allarm.class);
		List<Allarm> allAllarms = findQuery.getResultList();
		// assertEquals(0L, allAllarms.size());
		assertNotNull(getEntityManager());
	}

	@Test
	public void userTest() {
		TypedQuery<Users> findQuery = getEntityManager()
				.createQuery("Select u from Users u where u.key.username = :username", Users.class);
		findQuery.setParameter("username", "admin");
		List<Users> allUsers = findQuery.getResultList();
		assertNotNull(getEntityManager());
	}

	@Test
	public void userTestAndRole() {
		TypedQuery<Users> findQuery = getEntityManager().createQuery(
				"Select u from Users u where u.key.username = :username and u.key.role = :role", Users.class);
		findQuery.setParameter("username", "admin");
		findQuery.setParameter("role", "ROLE_SADMIN");
		List<Users> allUsers = findQuery.getResultList();
		assertNotNull(getEntityManager());
	}

}
