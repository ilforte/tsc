/**
 * 
 */
package it.tsc.security.provider;

import java.util.Collection;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import it.tsc.model.ApplicationUser;
import it.tsc.service.UserService;

/**
 * @author astraservice
 *
 */
@Component("authenticationProvider")
public class CustomAuthenticationProvider implements AuthenticationProvider {
  private UserService userService;

  /**
   * 
   */
  public CustomAuthenticationProvider(UserService userService) {
    this.userService = userService;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.springframework.security.authentication.AuthenticationProvider#authenticate(org.
   * springframework.security.core.Authentication)
   */
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String password = authentication.getCredentials().toString().trim();
    String username = authentication.getName().toString().trim();
    Authentication auth = null;

    // Authenticate the user based on your custom logic
    Collection<GrantedAuthority> grantedAuths = userService.getUserRoles(username, password);

    // TODO remove when service is created
    if (grantedAuths != null) {
      ApplicationUser appUser = new ApplicationUser(username, password, true, true, true, true,
          grantedAuths, "TestEmail");
      auth = new UsernamePasswordAuthenticationToken(appUser, password, grantedAuths);
      return auth;
    } else {
      return null;
    }

    // TODO add when service is created
    // if (grantedAuths != null) {
    // ApplicationUser appUser = new ApplicationUser(userName, password, true, true, true, true,
    // grantedAuths, "TestEmail");
    // auth = new UsernamePasswordAuthenticationToken(appUser, password, grantedAuths);
    // return auth;
    // } else {
    // return null;
    // }
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.springframework.security.authentication.AuthenticationProvider#supports(java.lang.Class)
   */
  public boolean supports(Class<?> authentication) {
    return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
  }

}
