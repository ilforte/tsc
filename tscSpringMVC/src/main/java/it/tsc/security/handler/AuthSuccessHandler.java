/**
 * 
 */
package it.tsc.security.handler;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

/**
 * @author astraservice
 *
 */
public class AuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
  private static Logger logger = LoggerFactory.getLogger(AuthSuccessHandler.class);
  private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {
    handle(request, response, authentication);
    clearAuthenticationAttributes(request);
  }

  @Override
  protected void handle(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException {

    String targetUrl = determineTargetUrl(authentication);

    if (response.isCommitted()) {
      logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
      return;
    }

    redirectStrategy.sendRedirect(request, response, targetUrl);
  }

  /**
   * determin target url based on Authentication
   * 
   * @param authentication
   * @return
   */
  protected String determineTargetUrl(Authentication authentication) {
    boolean isUser = false;
    boolean isAdmin = false;
    boolean isImpersonate = false;
    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
    for (GrantedAuthority grantedAuthority : authorities) {
      if (grantedAuthority.getAuthority().equals("ROLE_USER")) {
        isUser = true;
        break;
      } else if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
        isAdmin = true;
        break;
      } else if (grantedAuthority.getAuthority().equals("ROLE_IMPERSONATE")) {
        isImpersonate = true;
        break;
      }
    }

    if (isUser) {
      return "/user";
    } else if (isAdmin) {
      return "/admin";
    } else if (isImpersonate) {
      return "/admin";
    } else {
      throw new IllegalStateException();
    }
  }

  @Override
  public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
    this.redirectStrategy = redirectStrategy;
  }

  @Override
  protected RedirectStrategy getRedirectStrategy() {
    return redirectStrategy;
  }


}
