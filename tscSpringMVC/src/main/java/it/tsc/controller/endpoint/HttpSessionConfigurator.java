/**
 * 
 */
package it.tsc.controller.endpoint;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

/**
 * @author astraservice Server Endpoint session configurator
 */
public class HttpSessionConfigurator extends ServerEndpointConfig.Configurator {
  @Override
  public void modifyHandshake(ServerEndpointConfig config, HandshakeRequest request,
      HandshakeResponse response) {
    HttpSession httpSession = (HttpSession) request.getHttpSession();
    if (httpSession != null) {
      config.getUserProperties().put("HTTP_SESSION", httpSession);
    }
  }
}
