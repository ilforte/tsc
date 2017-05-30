/**
 * 
 */
package it.tsc.controller.endpoint;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import it.tsc.model.Allarm;
import it.tsc.util.TimeUtil;

/**
 * @author astraservice
 *
 */
@ServerEndpoint("/admin/allarmEndpoint")
public class WebSocketAllarmController {
  private static Logger logger = LoggerFactory.getLogger(WebSocketAllarmController.class);
  private static ScheduledExecutorService timer = Executors.newSingleThreadScheduledExecutor();
  private static Set<Session> allSessions;

  /**
   * 
   */
  public WebSocketAllarmController() {
    // TODO Auto-generated constructor stub
  }

  @OnOpen
  public void openSession(Session session) {
    allSessions = session.getOpenSessions();

    // start the scheduler on the very first connection
    // to call sendTimeToAll every second
    logger.debug("openSession {}", session);
    if (session != null) {
      allSessions.add(session);
    }
    if (allSessions.size() == 1) {
      timer.scheduleAtFixedRate(() -> checkAllarmOnDatabase(session), 0, 3, TimeUnit.SECONDS);
    }
  }

  @OnError
  public void error(Session session, Throwable t) {
    allSessions.remove(session);
    logger.error("Error on session " + session.getId());
  }

  private void checkAllarmOnDatabase(Session session) {
    allSessions = session.getOpenSessions();
    for (Session sess : allSessions) {
      try {
        /**
         * Open database connection and send message
         */
        Allarm allarm = new Allarm();
        allarm.setData_arrivo(TimeUtil.getCurrentTimeStamp());
        Gson gson = new Gson();
        logger.debug("send message: {}", gson.toJson(allarm));
        sess.getBasicRemote().sendText(gson.toJson(allarm));
      } catch (IOException ioe) {
        logger.error(ioe.getMessage());
      }
    }
  }

  @OnClose
  public void onClose(Session session) {
    allSessions.remove(session);
    logger.debug("Client disconnected @ {}", session.getId());
  }

}
