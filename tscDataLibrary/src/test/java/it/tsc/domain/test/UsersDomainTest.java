/**
 * 
 */
package it.tsc.domain.test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.Test;

import it.tsc.domain.Group;
import it.tsc.domain.Role;
import it.tsc.domain.Users;
import it.tsc.domain.key.CompoundKey;
import it.tsc.util.ConversionUtil;

/**
 * @author astraservice
 *
 */
public class UsersDomainTest extends BaseDomainTest {

	@Test
	public void testUsers() {
		CompoundKey key1 = new CompoundKey("matteo", Role.ROLE_ADMIN);
		Users users1 = new Users(key1, true);

		CompoundKey key2 = new CompoundKey("matteo", Role.ROLE_USER);
		Users users2 = new Users(key2, true);

		Group group1 = new Group(key1, "MILANO");
		Group group2 = new Group(key1, "NAPOLI");
		Group group3 = new Group(key2, "NAPOLI");

		Set<Group> g = new HashSet<Group>();

		getEntityManager().persist(group1);
		getEntityManager().persist(group2);
		getEntityManager().persist(group3);
		getEntityManager().persist(users1);
		getEntityManager().persist(users2);
		logger.debug("groups: {}", getEntityManager().isOpen());
	}

	@Test
	public void testExecuteScript() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		Query query = getEntityManager().createNativeQuery("TRUNCATE table ks_tsc.tb_groups");
		query.executeUpdate();
	}

	@Test
	public void testJsonScript1() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		Query query = getEntityManager().createNativeQuery("SELECT username,role,groupName from ks_tsc.tb_groups",
				Group.class);
		logger.debug("query: {}", ConversionUtil.getGsonConverter().toJson(query.getResultList()));
		query.executeUpdate();
	}

	@Test
	public void testJsonScript2() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		Users users1 = new Users(new CompoundKey("matteo", Role.ROLE_ADMIN), true);
		Query query = getEntityManager().createNativeQuery(
				"SELECT username,role,groupName from ks_tsc.tb_groups WHERE role='ROLE_ADMIN' ALLOW FILTERING",
				Group.class);
		// query.setParameter("role", users1.getKey().getRole());
		logger.debug("query: {}", ConversionUtil.getGsonConverter().toJson(query.getResultList()));
		query.executeUpdate();
	}

	@Test
	public void testScript3() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		Users users1 = new Users(new CompoundKey("matteo", Role.ROLE_USER), true);
		Query query = getEntityManager().createNativeQuery(
				"SELECT username,role,groupName from ks_tsc.tb_groups WHERE role='ROLE_ADMIN' ALLOW FILTERING",
				Group.class);
		// query.setParameter("role", users1.getKey().getRole());
		logger.debug("query: {}", ConversionUtil.getGsonConverter().toJson(query.getResultList()));
		query.executeUpdate();
	}

	@Test
	public void testJsonScript3() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		TypedQuery<Users> query = getEntityManager().createNamedQuery(Users.SELECT_ALL_USERS, Users.class);
		List<Users> u = query.getResultList();
		// logger.debug("groups2 size: {}", gr2.size());
		getEntityManager().close();
	}

	@Test
	public void testScript4() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		Query query = getEntityManager().createNativeQuery(
				"SELECT username,role,groupName from ks_tsc.tb_groups WHERE role='ROLE_ADMIN' ALLOW FILTERING",
				Group.class);
		// query.setParameter("role", users1.getKey().getRole());
		logger.debug("query: {}", ConversionUtil.getGsonConverter().toJson(query.getResultList()));
		query.executeUpdate();
	}

}
