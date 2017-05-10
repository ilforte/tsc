/**
 * 
 */
package it.tsc.authentication.provider;

import java.util.Collection;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
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
@Component("customAuthenticationProvider")
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

    // 2. Check the passwords match (should use a hashed password here).
    if ("".equals(username) || "".equals(password)) {
      throw new BadCredentialsException("Bad Credentials, Insert Username and Password!");
    }

    // Authenticate the user based on your custom logic
    Collection<GrantedAuthority> grantedAuths = userService.getUserRoles(username, password);

    // TODO remove when service is created
    if (grantedAuths != null) {
      // TODO add email
      ApplicationUser appUser = new ApplicationUser(username, password, true, true, true, true,
          grantedAuths, "TestEmail");
      auth = new UsernamePasswordAuthenticationToken(appUser, password, grantedAuths);
      return auth;
    } else {
      throw new BadCredentialsException("Bad Credentials, Wrong Username or Password!");
    }
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
