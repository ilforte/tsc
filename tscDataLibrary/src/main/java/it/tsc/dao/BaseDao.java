/**
 * 
 */
package it.tsc.dao;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author astraservice
 *
 */
public class BaseDao {
	@Autowired
	private EntityManager entityManager;

	/**
	 * 
	 */
	public BaseDao() {

	}

	public EntityManager getEntityManager() {
		return this.entityManager;
	}

}
