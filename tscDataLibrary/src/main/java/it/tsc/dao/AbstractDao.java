/**
 * 
 */
package it.tsc.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author astraservice
 *
 */
public abstract class AbstractDao {
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;

	/**
	 * 
	 */
	public AbstractDao() {

	}

	protected EntityManager getEntityManager() {
		if (entityManagerFactory != null) {
			if (entityManager == null) {
				entityManager = entityManagerFactory.createEntityManager();
				return entityManager;
			} else if (!entityManager.isOpen()) {
				entityManager = entityManagerFactory.createEntityManager();
				return entityManager;
			} else {
				return entityManager;
			}
		} else {
			throw new RuntimeException("entityManagerFactory cannot be null");
		}
	}

}
