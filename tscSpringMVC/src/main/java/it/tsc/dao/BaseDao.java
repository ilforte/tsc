/**
 * 
 */
package it.tsc.dao;

import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Cluster.Builder;
import com.datastax.driver.core.QueryOptions;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.MappingManager;

/**
 * @author astraservice
 *
 */
@Repository
public class BaseDao implements InitializingBean {
  private static Logger logger = LoggerFactory.getLogger(BaseDao.class);

  @Value("${cassandra-nodes}")
  private String nodes;

  @Value("#{T(java.lang.Integer).valueOf('${cassandra-port}')}")
  private Integer port;

  @Value("${cassandra-username}")
  private String username;

  @Value("${cassandra-password}")
  private String password;

  /** Cassandra Cluster. */
  private Cluster cluster;
  /** Cassandra Session. */
  private Session session;
  /** Cassandra MappingManager. */
  private MappingManager manager;

  public BaseDao() {
    super();
  }


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
    b.withQueryOptions(new QueryOptions());
    cluster = b.build();
    session = cluster.connect();
    /**
     * prepare mapping manager
     */
    manager = new MappingManager(session);
    logger.debug("Cluster name {} metadata: {}", cluster.getMetadata());
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

  @PreDestroy
  public void cleanUp() throws Exception {
    logger.debug("Spring Container is destroy");
    this.close();
  }

  public MappingManager getMappingManager() {
    return manager;
  }


  public void afterPropertiesSet() throws Exception {
    logger.debug("Init method after properties are set and connect ");
    this.connect();
  }

}
