/**
 * 
 */
package it.tsc.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import it.tsc.authentication.AuthSuccessHandler;

/**
 * @author astraservice
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  private static Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

  @Autowired
  private AuthSuccessHandler authSuccessHandler;

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) {
    try {
      auth.inMemoryAuthentication().withUser("mkyong").password("123456").roles("USER");
      auth.inMemoryAuthentication().withUser("admin").password("123456").roles("ADMIN");
      auth.inMemoryAuthentication().withUser("dba").password("123456").roles("DBA");
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/resources/**"); // #3
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // @formatter:off
    http.authorizeRequests()
    .antMatchers("/","/welcome").permitAll() // #4
    .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
    .antMatchers("/dba/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_DBA')")
    .anyRequest().authenticated() // 7
    .and()
    .formLogin();  // #8
    // @formatter:on
  }

}
