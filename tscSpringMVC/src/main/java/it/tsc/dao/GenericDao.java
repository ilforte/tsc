/**
 * 
 */
package it.tsc.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Cluster.Builder;
import com.datastax.driver.core.Session;

/**
 * @author astraservice
 *
 */
public class GenericDao {
  private static Logger logger = LoggerFactory.getLogger(GenericDao.class);

  private String nodes;

  private Integer port;

  private String username;

  private String password;

  /** Cassandra Cluster. */
  private Cluster cluster;

  public GenericDao(String nodes, Integer port, String username, String password) {
    super();
    this.nodes = nodes;
    this.port = port;
    this.username = username;
    this.password = password;
    /**
     * connect to cassandra
     */
    if (cluster == null || session == null) {
      connect();
    }
  }

  /** Cassandra Session. */
  private Session session;


  /**
   * Connect to Cassandra Cluster specified by provided node IP address and port number.
   * AbstractDataAccess
   * 
   * @param node Cluster node IP address.
   * @param port Port of cluster host.
   */
  private void connect() {
    Builder b = Cluster.builder();
    b.addContactPoints(nodes.split(","));
    b.withPort(port);
    b.withCredentials(username, password);
    cluster = b.build();
    session = cluster.connect();
    logger.debug("Cluster name {} metadata: {}", cluster.getClusterName(), cluster.getMetadata());
  }

  /**
   * Provide my Session.
   *
   * @return My session.
   */
  public Session getSession() {
    return this.session;
  }

  /** Close cluster. */
  public void close() {
    session.close();
    cluster.close();
  }

}
