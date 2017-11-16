/**
 * 
 */
package it.tsc.domain.test;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.code.tempusfugit.concurrency.ConcurrentTestRunner;

import it.tsc.domain.Group;
import it.tsc.domain.Role;
import it.tsc.domain.Users;
import it.tsc.domain.key.CompoundKey;
import it.tsc.util.JsonUtil;

/**
 * @author astraservice
 *
 */
@RunWith(ConcurrentTestRunner.class)
public class UsersDomainTest extends BaseDomainTest {

	@Test
	public void testExecuteScript() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		Query query = getEntityManager().createNativeQuery("TRUNCATE table ks_tsc.tb_users");
		query.executeUpdate();
		query = getEntityManager().createNativeQuery("TRUNCATE table ks_tsc.tb_groups");
		query.executeUpdate();
	}

	@Test
	public void testJsonScript1() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		Query query = getEntityManager().createNativeQuery("SELECT username,role,groupName from ks_tsc.tb_groups",
				Group.class);
		logger.debug("query: {}", JsonUtil.getGsonConverter().toJson(query.getResultList()));
		query.executeUpdate();
	}

	@Test
	public void testJsonScript2() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		Users users1 = new Users(new CompoundKey("matteo", Role.ROLE_ADMIN), true);
		Query query = getEntityManager().createNativeQuery(
				"SELECT username,role,groupName from ks_tsc.tb_groups WHERE role='ROLE_ADMIN' ALLOW FILTERING",
				Group.class);
		// query.setParameter("role", users1.getKey().getRole());
		logger.debug("query: {}", JsonUtil.getGsonConverter().toJson(query.getResultList()));
		query.executeUpdate();
	}

	@Test
	public void testScript3() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		Users users1 = new Users(new CompoundKey("matteo", Role.ROLE_USER), true);
		Query query = getEntityManager().createNativeQuery(
				"SELECT username,role,groupName from ks_tsc.tb_groups WHERE role='ROLE_ADMIN' ALLOW FILTERING",
				Group.class);
		// query.setParameter("role", users1.getKey().getRole());
		logger.debug("query: {}", JsonUtil.getGsonConverter().toJson(query.getResultList()));
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
		logger.debug("query: {}", JsonUtil.getGsonConverter().toJson(query.getResultList()));
		query.executeUpdate();
	}

}
