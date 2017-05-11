/**
 * 
 */
package it.tsc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import it.tsc.dao.GenericDao;

/**
 * @author astraservice
 *
 */
@Configuration
@ComponentScan(basePackages = {"it.tsc.service.impl", "it.tsc.dao.impl"})
@PropertySource(value = {"classpath:cassandra.properties"}, ignoreResourceNotFound = false)
public class ServiceConfig {
  @Value("${cassandra-nodes}")
  private String nodes;

  @Value("#{T(java.lang.Integer).valueOf('${cassandra-port}')}")
  private Integer port;

  @Value("${cassandra-username}")
  private String username;

  @Value("${cassandra-password}")
  private String password;

  /**
   * 
   */
  public ServiceConfig() {
    // TODO Auto-generated constructor stub
  }

  /**
   * Property placeholder configurer needed to process @Value annotations
   */
  @Bean
  public static PropertySourcesPlaceholderConfigurer propertyConfigurerabstractDao() {
    return new PropertySourcesPlaceholderConfigurer();
  }

  /**
   * Generic dao for cassandra
   * 
   * @return
   */
  @Bean(name = "genericDao")
  public GenericDao genericDao() {
    return new GenericDao(nodes, port, username, password);
  }

}
