/**
 * 
 */
package it.tsc.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * @author astraservice
 *
 */

public class CustomLogoutHandler implements LogoutSuccessHandler {
  private static Logger logger = LoggerFactory.getLogger(CustomLogoutHandler.class);

  @Override
  public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {
    /**
     * invalidate session
     */
    logger.debug("invalidate session");
    request.getSession().invalidate();
  }

}
