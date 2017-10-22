/**
 * 
 */
package it.tsc.controller.endpoint;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

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
  private static Thread publisherThread; // rate publisher thread
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
      publisherThread = new Thread() {
        @Override
        public void run() {
          while (true) {
            if (queue != null)
              sendMessageToAll(allarmService.jsonGetAllarms());
            try {
              sleep(3000);
            } catch (InterruptedException e) {
            }
          }
        };
      };
      publisherThread.start();
    }


    // if (queue.size() != 0) {
    // if (service == null) {
    // /**
    // * activate executor if not exist
    // */
    // ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
    // service.scheduleAtFixedRate(() -> checkAllarmOnDatabase(session), 0, 3, TimeUnit.SECONDS);
    // }
    // }
  }

  @OnError
  public void error(Session session, Throwable t) {
    System.err.println("Error on session " + session.getId());
    logger.debug("Error on session " + session.getId());
    try {
      publisherThread.join();
    } catch (InterruptedException e) {
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
          session.getBasicRemote().sendText(msg);
        }
      }
      queue.removeAll(closedSessions);
    } catch (Throwable e) {
      e.printStackTrace();
    }
  }


  private void checkAllarmOnDatabase(Session session) {
    /**
     * Open database connection and send message
     */
    /**
     * broadcast message
     */
    // synchronized (clients) {
    // for (Session sess : session.getOpenSessions()) {
    // if (sess.isOpen()) {
    // try {
    // String result = jobResult.getResult();
    // sess.getBasicRemote().sendText(result);
    // logger.debug("send result: ", result);
    // } catch (IOException ex) {
    // logger.error(ex.getMessage());
    // }
    // }
    // }
    // }
  }

  @OnClose
  public void onClose(Session session) {
    queue.remove(session);
    logger.debug("session closed:  " + session.getId());
    try {
      publisherThread.join();
    } catch (InterruptedException e) {
      logger.error("onClose: {}", e);
    }
  }

  /**
   * destroy scheduler avoiding leak
   */
  public static void destroyScheduler() {
    // if (service != null && clients.size() == 0) {
    // service.shutdown();
    // }
    if (publisherThread != null) {
      try {
        publisherThread.join();
      } catch (InterruptedException e) {
        logger.error("destroyScheduler: {}", e);
      }
    }
    if (queue != null) {
      queue.remove();
    }
  }
}
