/**
 * 
 */
package it.tsc.data.config;

import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
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
@ComponentScan(basePackages = { "it.tsc.service", "it.tsc.dao" })
@PropertySource(value = { "classpath:cassandra.properties" }, ignoreResourceNotFound = false)
public class ServiceConfig {
	private static Logger logger = LoggerFactory.getLogger(ServiceConfig.class);
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

	@Bean(name = "bcryptEncoder")
	public BCryptPasswordEncoder bcryptEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean(name = "entityManagerFactory")
	/**
	 * 
	 * @return
	 */
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setPersistenceUnitName(PERSISTENCE_UNIT);
		return entityManagerFactory;
	}

	@Bean(name = "entityManager")
	public EntityManager entityManager(@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
		EntityManager entityManager = null;
		if (entityManagerFactory != null) {
			if (entityManager == null || (entityManager != null && !entityManager.isOpen())) {
				entityManager = entityManagerFactory.createEntityManager();
			}
		} else {
			throw new RuntimeException("entityManagerFactory cannot be null");
		}
		return entityManager;
	}

	@PreDestroy
	public void cleanUp() throws Exception {
		logger.debug("Spring Container is destroy! Customer clean up");
	}

}
