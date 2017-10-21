/**
 * 
 */
package it.tsc.config;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.impetus.client.cassandra.common.CassandraConstants;

/**
 * @author astraservice
 *
 */
@Configuration
@ComponentScan(basePackages = {"it.tsc.service.impl", "it.tsc.dao"})
@ImportResource({"classpath:spring-beans.xml"})
@PropertySource(value = {"classpath:cassandra.properties"}, ignoreResourceNotFound = false)
public class ServiceConfig {
  @Value("${cassandra-persistence-unit}")
  private String PERSISTENCE_UNIT;
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
  public static PropertySourcesPlaceholderConfigurer propertyConfigurerAbstractDao() {
    return new PropertySourcesPlaceholderConfigurer();
  }
  
  @Bean(name="entityManagerFactory")
  public EntityManagerFactory entityManagerFactory() {
    Map<String, String> props = new HashMap<String, String>();
    props.put(CassandraConstants.CQL_VERSION, CassandraConstants.CQL_VERSION_3_0);
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT, props);
    return entityManagerFactory;  
  }

}
