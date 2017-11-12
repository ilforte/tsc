/**
 * 
 */
package it.tsc.data.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author astraservice
 *
 */
@Configuration
@ComponentScan(basePackages = { "it.tsc.service.impl", "it.tsc.dao" })
@PropertySource(value = { "classpath:cassandra.properties" }, ignoreResourceNotFound = false)
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

	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setPersistenceUnitName(PERSISTENCE_UNIT);
		return entityManagerFactory;
	}

	@Bean(name = "bcryptEncoder")
	public BCryptPasswordEncoder bcryptEncoder() {
		return new BCryptPasswordEncoder();
	}

}
