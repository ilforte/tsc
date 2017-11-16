/**
 * 
 */
package it.tsc.test.persistence;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import com.impetus.client.cassandra.common.CassandraConstants;

import it.tsc.test.parallel.ParallelTest;

/**
 * @author astraservice
 *
 */
public class PersistenceTest extends ParallelTest {

  @Test
  public void testPersistence() {
    // enable CQL3
    Map<String, Object> props = new HashMap<>();
    props.put(CassandraConstants.CQL_VERSION, CassandraConstants.CQL_VERSION_3_0);
    props.put("kundera.nodes", "localhost");
    props.put("kundera.port", "9042");
    props.put("kundera.username", "cassandradbuser");
    props.put("kundera.password", "v7^bEWnuHaFXtXfv");
    props.put("kundera.keyspace", "ks_tsc");
    props.put("kundera.dialect", "cassandra");
    props.put("kundera.client.lookup.class",
        "com.impetus.kundera.client.cassandra.dsdriver.DSClientFactory");

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("cassandra_pu", props);
    EntityManager em = emf.createEntityManager();
  }

}
