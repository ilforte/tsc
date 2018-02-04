/**
 * 
 */
package it.tsc.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.tsc.controller.endpoint.WebSocketAllarmController;

/**
 * @author astraservice Manage app session listener
 */
public class AppSessionListener implements HttpSessionListener {
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
    WebSocketAllarmController weController = new WebSocketAllarmController();
    weController.destroyScheduler(sessionEvent);
    sessionEvent.getSession().invalidate();
  }

}
