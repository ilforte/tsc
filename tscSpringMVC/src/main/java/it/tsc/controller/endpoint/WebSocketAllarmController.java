/**
 * 
 */
package it.tsc.controller.endpoint;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
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

import com.google.gson.Gson;

import it.tsc.model.Allarm;
import it.tsc.model.Result;
import it.tsc.util.TimeUtil;

/**
 * @author astraservice
 *
 */
@ServerEndpoint(value = "/admin/allarmEndpoint", configurator = HttpSessionConfigurator.class)
public class WebSocketAllarmController {
  private static Logger logger = LoggerFactory.getLogger(WebSocketAllarmController.class);
  private static ScheduledExecutorService service = null;
  private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());

  /**
   * 
   */
  public WebSocketAllarmController() {
    // TODO Auto-generated constructor stub
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
        service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(() -> checkAllarmOnDatabase(), 0, 3, TimeUnit.SECONDS);
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


  private void checkAllarmOnDatabase() {
    /**
     * Open database connection and send message
     */
    Allarm allarm = new Allarm();
    allarm.setData_arrivo(TimeUtil.getCurrentTimeStamp());
    allarm.setAb_codi("N0005");
    allarm.setEvento("1");
    Gson gson = new Gson();
    List<Allarm> allarms = new ArrayList<Allarm>();
    Result result = new Result();
    allarms.add(allarm);
    result.setData(allarms);
    /**
     * broadcast message
     */
    synchronized (clients) {
      for (Session session : clients) {
        if (session.isOpen()) {
          try {
            logger.debug("size: {}  client: {} send message: {}", clients.size(), session.getId(),
                gson.toJson(allarms));
            session.getBasicRemote().sendText(gson.toJson(allarms));
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
