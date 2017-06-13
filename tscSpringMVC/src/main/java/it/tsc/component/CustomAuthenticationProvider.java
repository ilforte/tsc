/**
 * 
 */
package it.tsc.component;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import it.tsc.model.ApplicationUser;
import it.tsc.model.PortalUser;
import it.tsc.service.UserService;

/**
 * @author astraservice
 *
 */
@Component("customAuthenticationProvider")
public class CustomAuthenticationProvider implements AuthenticationProvider {
  @Autowired
  private BCryptPasswordEncoder bcryptEncoder;
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
  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    Collection<GrantedAuthority> grantedAuths = null;
    String password = authentication.getCredentials().toString().trim();
    String username = authentication.getName().toString().trim();
    Authentication auth = null;

    // 2. Check the passwords match (should use a hashed password here).
    if ("".equals(username) || "".equals(password)) {
      throw new BadCredentialsException("Bad Credentials, Insert Username and Password!");
    }
    // get user
    PortalUser currentUser = userService.getUser(username);
    /**
     * check password
     */
    if (currentUser != null && bcryptEncoder.matches(password, currentUser.getPassword())) {
      // Authenticate the user based on your custom logic
      grantedAuths = userService.getUserRoles(username);
    } else {
      throw new BadCredentialsException("Bad Credentials, Wrong Username or Password!");
    }

    // TODO remove when service is created
    if (grantedAuths != null) {
      // TODO add email
      ApplicationUser appUser = new ApplicationUser(username, password, true, true, true, true,
          grantedAuths, currentUser.getEmail());
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
  @Override
  public boolean supports(Class<?> authentication) {
    return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
  }

}
