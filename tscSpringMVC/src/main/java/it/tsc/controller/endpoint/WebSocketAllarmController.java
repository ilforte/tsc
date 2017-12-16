/**
 * 
 */
package it.tsc.controller.endpoint;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.server.standard.SpringConfigurator;

import it.tsc.service.AllarmService;

/**
 * @author astraservice
 *
 */
@Controller
@ServerEndpoint(value = "/user/allarmEndpoint", configurator = SpringConfigurator.class)
@Service
public class WebSocketAllarmController {
  private static Logger logger = LoggerFactory.getLogger(WebSocketAllarmController.class);
  // private static ScheduledExecutorService service = null;
  // queue holds the list of connected clients
  private static Queue<Session> queue = new ConcurrentLinkedQueue<Session>();
  private static final ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
  @Autowired
  private AllarmService allarmService;

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
    queue.add(session);
    logger.debug("New session opened: {}", session.getId());
    if (queue.size() != 0) {
      ses.scheduleWithFixedDelay(new Runnable() {
        @Override
        public void run() {
          sendMessageToAll(allarmService.jsonGetAllarms());
        }
      }, 0, 3, TimeUnit.SECONDS);
    }
  }

  @OnError
  public void error(Session session, Throwable t) {
    System.err.println("Error on session " + session.getId());
    logger.debug("Error on session " + session.getId());
    try {
      ses.shutdown();
    } catch (Exception e) {
      logger.error("error: {}", e);
    }
  }


  private static void sendMessageToAll(String msg) {
    try {
      /* Send the new rate to all open WebSocket sessions */
      ArrayList<Session> closedSessions = new ArrayList<>();
      for (Session session : queue) {
        if (!session.isOpen()) {
          logger.error("Closed session: " + session.getId());
          closedSessions.add(session);
        } else if (msg != null && StringUtils.isNotEmpty(msg)) {
          // logger.debug("send message: {}", msg);
          session.getBasicRemote().sendText(msg);
        }
      }
      queue.removeAll(closedSessions);
    } catch (Throwable e) {
      e.printStackTrace();
    }
  }

  @OnClose
  public void onClose(Session session) {
    queue.remove(session);
    logger.debug("session closed:  " + session.getId());
    try {
      ses.shutdown();
    } catch (Exception e) {
      logger.error("onClose: {}", e);
    }
  }

  /**
   * destroy scheduler avoiding leak
   */
  public static void destroyScheduler() {
    if (!ses.isTerminated()) {
      try {
        ses.shutdown();
      } catch (Exception e) {
        logger.error("destroyScheduler: {}", e);
      }
    }
    if (queue != null && queue.size() != 0) {
      queue.remove();
    }
  }
}
