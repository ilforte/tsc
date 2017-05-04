/**
 * 
 */
package it.tsc.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import it.tsc.handler.AuthSuccessHandler;
import it.tsc.handler.CustomAccessDeniedHandler;

/**
 * @author astraservice
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  private static Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

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
    web.ignoring().antMatchers("/resources/**"); // #3login?logout
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // @formatter:offcustomAccessDeniedHandler
    http.authorizeRequests()
    .antMatchers("/","/welcome").permitAll() // #4
    .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
    .antMatchers("/user/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    .anyRequest().authenticated() // 7
    .and()
    .formLogin()  // #8
    .successHandler(authSuccessHandler());
    http.exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
    // @formatter:on
  }

  @Bean(name = "authSuccessHandler")
  public AuthSuccessHandler authSuccessHandler() {
    return new AuthSuccessHandler();
  }

  @Bean(name = "customAccessDeniedHandler")
  public CustomAccessDeniedHandler customAccessDeniedHandler() {
    CustomAccessDeniedHandler customAccessDeniedHandler = new CustomAccessDeniedHandler();
    customAccessDeniedHandler.setErrorPage("403");
    return customAccessDeniedHandler;
  }

}
