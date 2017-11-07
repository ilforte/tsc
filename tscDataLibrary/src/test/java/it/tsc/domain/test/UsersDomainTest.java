/**
 * 
 */
package it.tsc.domain.test;

import java.util.List;

import javax.persistence.TypedQuery;

import org.junit.Test;

import it.tsc.domain.Group;
import it.tsc.domain.Role;
import it.tsc.domain.Users;

/**
 * @author astraservice
 *
 */
public class UsersDomainTest extends BaseDomainTest {

	@Test
	public void userUsers() {
		Users users = new Users();
		users.addRole(Role.ROLE_ADMIN);
		users.addUsername("matteo");
		users.setMfaEnabled(true);

		Group group1 = new Group(users.getKey(), "MILANO");
		Group group2 = new Group(users.getKey(), "NAPOLI");

		users.getGroups().add(group1);
		users.getGroups().add(group2);

		getEntityManager().persist(group1);
		getEntityManager().persist(group2);
		getEntityManager().persist(users);

		logger.debug("getGroups {}", users.getGroups());

		TypedQuery<Users> query = getEntityManager().createNamedQuery("SELECT u FROM Users u", Users.class);
		List<Users> u = query.getResultList();
		for (Users users2 : u) {
			logger.debug("users2 list: {} username: {}", users2, users2.getKey().getUsername());
			logger.debug("groups2 list: {}", users2.getGroups());
			// for (Group group : users2.getGroups()) {
			// logger.debug("group list: {}", group);
			// }
		}

		getEntityManager().close();
		logger.debug("groups: {}", getEntityManager().isOpen());
	}

}
