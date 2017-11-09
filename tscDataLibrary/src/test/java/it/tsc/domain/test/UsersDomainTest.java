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
		Users users = new Users();
		users.addRole(Role.ROLE_ADMIN);
		users.addUsername("matteo");
		users.setMfaEnabled(true);

		Users users1 = new Users(new CompoundKey("matteo", Role.ROLE_USER), "", "", true, "", "");

		Group group1 = new Group(users.getKey(), "MILANO");
		Group group2 = new Group(users.getKey(), "NAPOLI");
		Group group3 = new Group(users1.getKey(), "MILANO");

		Set<Group> g = new HashSet<Group>();

		getEntityManager().persist(group1);
		getEntityManager().persist(group2);
		getEntityManager().persist(group3);
		getEntityManager().persist(users);
		getEntityManager().persist(users1);

		TypedQuery<Users> query = getEntityManager().createNamedQuery(Users.SELECT_ALL_USERS, Users.class);
		List<Users> u = query.getResultList();
		TypedQuery<Group> query1 = getEntityManager().createNamedQuery(Group.SELECT_GROUPS, Group.class);
		List<Group> gr = query1.getResultList();
		logger.debug("groups size: {}", gr.size());
		TypedQuery<Group> query2 = getEntityManager().createNamedQuery(Group.SELECT_GROUPS_BY_KEY, Group.class);
		query2.setParameter("key", users.getKey());
		List<Group> gr2 = query2.getResultList();
		logger.debug("groups2 size: {}", gr2.size());
		getEntityManager().close();
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
		Users users1 = new Users(new CompoundKey("matteo", Role.ROLE_USER), "", "", true, "", "");
		Query query = getEntityManager().createNativeQuery(
				"SELECT username,role,groupName from ks_tsc.tb_groups WHERE role='ROLE_USER' ALLOW FILTERING",
				Group.class);
		// query.setParameter("role", users1.getKey().getRole());
		logger.debug("query: {}", ConversionUtil.getGsonConverter().toJson(query.getResultList()));
		query.executeUpdate();
	}

}
