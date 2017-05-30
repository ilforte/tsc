/**
 * 
 */
package it.tsc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * @author astraservice
 *
 */
@Configuration
@ComponentScan(basePackages = {"it.tsc.service.impl", "it.tsc.dao"})
@PropertySource(value = {"classpath:cassandra.properties"}, ignoreResourceNotFound = false)
public class ServiceConfig {

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

}
