/**
 * 
 */
package it.tsc.controller.endpoint;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.websocket.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.tsc.service.AllarmService;

/**
 * @author "astraservice"
 *
 */
public class AllarmGenerator implements Runnable {
  private static Logger logger = LoggerFactory.getLogger(AllarmGenerator.class);
  private AllarmService allarmService;
  private Queue<Session> queue = new ConcurrentLinkedQueue<Session>();

  @Override
  public void run() {
    try {
      /* Send the new rate to all open WebSocket sessions */
      if (queue.size() != 0 && oneOpenSessionAtLeast(queue)) {
        String msg = allarmService.jsonGetAllarms();
        for (Session session : queue) {
          if (session.isOpen()) {
            logger.debug("session opened: {} send message: {}", queue.size(), msg);
            session.getBasicRemote().sendText(msg);
          }
        }
      }
      // queue.removeAll(closedSessions);
    } catch (Throwable e) {
      e.printStackTrace();
    }
  }

  private boolean oneOpenSessionAtLeast(Queue<Session> queue) {
    boolean oneSessionOpenAtLeast = false;
    for (Session session : queue) {
      if (session.isOpen()) {
        oneSessionOpenAtLeast = true;
      }
    }
    return oneSessionOpenAtLeast;
  }

  public AllarmGenerator(AllarmService allarmService) {
    super();
    this.allarmService = allarmService;
  }

  public Queue<Session> getQueue() {
    return queue;
  }

}
