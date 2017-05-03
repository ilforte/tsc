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

import it.tsc.controller.LoginController;

/**
 * @author astraservice
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private static Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
	  @Autowired
	  public void configureGlobal(AuthenticationManagerBuilder auth){
	    try {
	    	auth
	        .inMemoryAuthentication()
	          .withUser("user")  // #1
	            .password("password")
	            .roles("USER")
	            .and()
	          .withUser("admin") // #2
	            .password("password")
	            .roles("ADMIN","USER");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}
	  }
	  
	  @Override
	  public void configure(WebSecurity web) throws Exception {
	    web
	      .ignoring()
	         .antMatchers("/resources/**"); // #3
	  }
	  
	  @Override
	  protected void configure(HttpSecurity http) throws Exception {
	    http.
	    authorizeRequests()
	    .antMatchers("/admin/**").hasRole("ADMIN")
	    .antMatchers("/user/**").hasRole("USER")
	    .anyRequest()
	    .authenticated()
	    .and()
	    . authorizeRequests()
	    .antMatchers("/login","/logout").permitAll(); // #4
	  }
}
