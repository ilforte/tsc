/**
 * 
 */
package it.tsc.domain.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import it.tsc.dao.UserDao;
import it.tsc.data.config.ServiceConfig;

/**
 * @author astraservice
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceConfig.class, loader = AnnotationConfigContextLoader.class)
public class UserDomainSpringTest {
	private static final Logger logger = LoggerFactory.getLogger(UserDomainSpringTest.class);
	@Autowired
	private UserDao userDao;

	@Test
	public void baseTest() {
		logger.debug("getAllUsers: {}", userDao.jsonGetAllUsers());
		assertNotNull(userDao);
	}

}
