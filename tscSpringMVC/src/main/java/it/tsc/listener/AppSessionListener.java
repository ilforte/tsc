/**
 * 
 */
package it.tsc.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author astraservice Manage app session listener
 */
public class AppSessionListener implements HttpSessionListener, ServletContextListener {
  private static Logger logger = LoggerFactory.getLogger(AppSessionListener.class);

  /**
   * 
   */
  public AppSessionListener() {

  }

  @Override
  public void sessionCreated(HttpSessionEvent sessionEvent) {
    logger.debug("session created: {}", sessionEvent);
  }

  @Override
  public void sessionDestroyed(HttpSessionEvent sessionEvent) {
    logger.debug("session destroyed: {}", sessionEvent);
  }

  @Override
  public void contextInitialized(ServletContextEvent servletContextEvent) {
    logger.debug("contextInitialized: {}", servletContextEvent);
  }

  @Override
  public void contextDestroyed(ServletContextEvent servletContextEvent) {
    logger.debug("contextDestroyed: {}", servletContextEvent);
  }

}
