/**
 * 
 */
package it.tsc.dao;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Cluster.Builder;
import com.datastax.driver.core.PreparedStatement;
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
  /**
   * cached statemens
   */
  private Map<String, PreparedStatement> cachedStatements;

  public BaseDao() {
    super();
    cachedStatements = new HashMap<String, PreparedStatement>();
  }


  /**
   * Connect to Cassandra Cluster specified by provided node IP address and port number.
   * AbstractDataAccessWatcherJob
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

  private Map<PreparedStatement, String> statements;

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
    logger.debug("BaseDao Spring Container is destroy");
    this.close();
  }

  public MappingManager getMappingManager() {
    return manager;
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    logger.debug("Init method after properties are set and connect ");
    this.connect();
  }

  /**
   * Return properly groupAccessor
   * 
   * @param <T>
   * 
   * @param <T>
   * @return
   * 
   * @return
   */
  public <T> T createAccessor(Class<T> klass) {
    MappingManager manager = this.getMappingManager();
    return manager.createAccessor(klass);
  }

  /**
   * Cache statement just prepared
   * 
   * @param sqlStatement
   * @return
   */
  public PreparedStatement prepareAndCacheStatement(String sqlStatement) {
    PreparedStatement preparedStmt;
    if (!cachedStatements.containsKey(sqlStatement)) {
      preparedStmt = this.getSession().prepare(sqlStatement);
      cachedStatements.put(sqlStatement, preparedStmt);
    } else {
      preparedStmt = cachedStatements.get(sqlStatement);
    }
    return preparedStmt;
  }

}
