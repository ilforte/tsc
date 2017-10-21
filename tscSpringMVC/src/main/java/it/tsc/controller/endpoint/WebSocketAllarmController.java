/**
 * 
 */
package it.tsc.controller.endpoint;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

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
  private static ScheduledExecutorService service = null;
  private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());

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
    HttpSession httpSession = (HttpSession) config.getUserProperties().get("HTTP_SESSION");
    if (httpSession != null) {
      httpSession.setAttribute("WEBSOCKET_SESSION", session);
      logger.debug("manage session attribute {}", session);
    }
    synchronized (clients) {
      logger.debug("add item to clients: {}", session.getId());
      clients.add(session);
    }

    if (clients.size() != 0) {
      if (service == null) {
        /**
         * activate executor if not exist
         */
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(() -> checkAllarmOnDatabase(session), 0, 5, TimeUnit.SECONDS);
      }
    }
  }

  @OnError
  public void error(Session session, Throwable t) {
    for (Session s : clients) {
      if (s.getId().equals(session.getId())) {
        clients.remove(session);
        logger.error("Error on session " + session.getId());
        return;
      }
    }
    logger.debug("no matching session " + session.getId());
  }


  private void checkAllarmOnDatabase(Session session) {
    /**
     * Open database connection and send message
     */
    /**
     * broadcast message
     */
    synchronized (clients) {
      for (Session sess : session.getOpenSessions()) {
        if (sess.isOpen()) {
          try {
            String result = allarmService.jsonGetAllarms();
            sess.getBasicRemote().sendText(result);
          } catch (IOException ex) {
            logger.error(ex.getMessage());
          }
        }
      }
    }
  }

  @OnClose
  public void onClose(Session session) {
    synchronized (clients) {
      for (Session s : clients) {
        if (s.getId().equals(session.getId())) {
          clients.remove(session);
          logger.debug("Client disconnected @ {}", session.getId());
        }
      }
    }
  }

  /**
   * destroy scheduler avoiding leak
   */
  public static void destroyScheduler() {
    if (service != null && clients.size() == 0) {
      service.shutdown();
    }
  }
}
