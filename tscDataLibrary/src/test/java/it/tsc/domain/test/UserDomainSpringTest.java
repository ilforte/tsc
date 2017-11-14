/**
 * 
 */
package it.tsc.domain.test;

import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import it.tsc.data.config.ServiceConfig;
import it.tsc.domain.Group;
import it.tsc.domain.Role;
import it.tsc.domain.Users;
import it.tsc.domain.key.CompoundKey;
import it.tsc.util.JsonUtil;

/**
 * @author astraservice
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceConfig.class, loader = AnnotationConfigContextLoader.class)
public class UserDomainSpringTest {
	private static final Logger logger = LoggerFactory.getLogger(UserDomainSpringTest.class);
	@Autowired
	private EntityManager entityManager;

	@Test
	public void baseTest() {
		assertNotNull(entityManager);
	}

	@Test
	public void testUsers() {
		CompoundKey key1 = new CompoundKey("matteo", Role.ROLE_ADMIN);
		Users users1 = new Users(key1, true);

		CompoundKey key2 = new CompoundKey("matteo", Role.ROLE_USER);
		Users users2 = new Users(key2, true);

		Group group1 = new Group(key1, "MILANO");
		Group group2 = new Group(key1, "NAPOLI");
		Group group3 = new Group(key2, "NAPOLI");

		entityManager.persist(group1);
		entityManager.persist(group2);
		entityManager.persist(group3);
		entityManager.persist(users1);
		entityManager.persist(users2);
		logger.debug("groups: {}", entityManager.isOpen());
		logger.debug(JsonUtil.returnJson(users1.getGroups(entityManager)));
	}

}
