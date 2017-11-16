/**
 * 
 */
package it.tsc.domain.test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.driver.core.Cluster;
import com.impetus.client.cassandra.common.CassandraConstants;
import com.impetus.kundera.client.cassandra.dsdriver.DSClientFactory;

/**
 * @author astraservice Class for Base Test Domain
 */
public class BaseDomain extends DSClientFactory {
	public final String PU = "cassandra_pu";
	private EntityManager em = null;
	protected static final Logger logger = LoggerFactory.getLogger(BaseDomain.class);
	private Map<String, Object> propertyMap = new HashMap<String, Object>();

	public BaseDomain() {
		super();
	}

	/**
	 * return EntityManager
	 * 
	 * @return
	 */
	protected EntityManager getEntityManager() {
		Map<String, String> props = new HashMap<String, String>();
		props.put(CassandraConstants.CQL_VERSION, CassandraConstants.CQL_VERSION_3_0);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU, props);
		if (em == null) {
			em = emf.createEntityManager();
		}
		return this.em;
	}

	protected Cluster getCluster() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		propertyMap.put("kundera.client.property", "persistence.xml");
		this.setExternalProperties(propertyMap);
		Object connection = this.createPoolOrConnection();
		Cluster cluster = (Cluster) connection;
		return cluster;
	}

}
