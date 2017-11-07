/**
 * 
 */
package it.tsc.domain.test;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.impetus.client.cassandra.common.CassandraConstants;

/**
 * @author astraservice Class for Base Test Domain
 */
public class BaseDomainTest {
	private final String _PU = "cassandra_pu";
	private EntityManager em = null;
	protected static final Logger logger = LoggerFactory.getLogger(BaseDomainTest.class);

	/**
	 * 
	 */
	public BaseDomainTest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * return EntityManager
	 * 
	 * @return
	 */
	protected EntityManager getEntityManager() {
		Map<String, String> props = new HashMap<String, String>();
		props.put(CassandraConstants.CQL_VERSION, CassandraConstants.CQL_VERSION_3_0);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(_PU, props);
		if (em == null) {
			em = emf.createEntityManager();
		}
		return this.em;
	}

}
