/**
 * 
 */
package it.tsc.controller.endpoint;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.server.standard.SpringConfigurator;

import it.tsc.job.AllarmNotifier;
import it.tsc.job.AllarmServiceJob;

/**
 * @author astraservice
 *
 */
@Controller
@ServerEndpoint(value = "/user/allarmEndpoint", configurator = SpringConfigurator.class)
@Service
public class WebSocketAllarmController implements AllarmNotifier {
  private static Logger logger = LoggerFactory.getLogger(WebSocketAllarmController.class);
  // private static ScheduledExecutorService service = null;
  // queue holds the list of connected clients
  private static Queue<Session> queue = new ConcurrentLinkedQueue<Session>();
  // private static final ScheduledExecutorService ses =
  // Executors.newSingleThreadScheduledExecutor();
  @Autowired
  private AllarmServiceJob allarmServiceJob;
  @Autowired
  private Map<String, Object> jobDataMap;

  /**
   * 
   */
  public WebSocketAllarmController() {

  }

  @OnOpen
  public void onOpenSession(Session session, EndpointConfig config) {
    /**
     * Manage session
     */
    Validate.notNull(allarmServiceJob, "allarmServiceJob cannot be null");
    Validate.notNull(jobDataMap, "jobDataMap cannot be null");

    /**
     * set Job data map
     */
    queue.add(session);
    if (jobDataMap.get("allarmNotifier") == null) {
      jobDataMap.put("allarmNotifier", this);
      jobDataMap.put("queue", queue);
    }
  }

  @OnError
  public void error(Session session, Throwable t) {
    System.err.println("Error on session " + session.getId());
    queue.remove(session);
    logger.debug("Error on session: {} Throwable: {}", session.getId(), t);
  }

  @OnClose
  public void onClose(Session session) {
    queue.remove(session);
    logger.debug("session closed:  " + session.getId());
  }



  @Override
  /**
   * fired allarms
   */
  public void onAllarmReceived(String message) {
    ArrayList<Session> closedSessions = new ArrayList<>();
    for (Session session : queue) {
      if (!session.isOpen()) {
        logger.error("Closed session: " + session.getId());
        closedSessions.add(session);
      } else {
        // logger.debug("send message: {}", msg);
        try {
          session.getBasicRemote().sendText(message);
        } catch (IOException e) {
          logger.error("onAllarmReceived Exception: {}", e);
        }
      }
    }
    if (closedSessions.size() != 0) {
      queue.removeAll(closedSessions);
    }
  }
}
