package it.tsc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import it.tsc.interceptor.PageRequestInterceptor;

@Configuration
@EnableWebMvc
@ComponentScan({"it.tsc.service", "it.tsc.dao", "it.tsc.security.provider", "it.tsc.controller"})
@Import({SecurityConfig.class})
public class AppConfig extends WebMvcConfigurerAdapter {

  public AppConfig() {

  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(31556926);
    registry.addResourceHandler("/img/**").addResourceLocations("/img/").setCachePeriod(31556926);
    registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(31556926);
  }

  @Override
  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
    super.configureDefaultServletHandling(configurer);
    configurer.enable();
  }

  @Bean
  public InternalResourceViewResolver viewResolver() {
    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
    viewResolver.setViewClass(JstlView.class);
    viewResolver.setPrefix("/WEB-INF/pages/");
    viewResolver.setSuffix(".jsp");
    return viewResolver;
  }

  /**
   * Configure TilesConfigurer.
   */
  @Bean
  public TilesConfigurer tilesConfigurer() {
    TilesConfigurer tilesConfigurer = new TilesConfigurer();
    tilesConfigurer.setDefinitions(new String[] {"/WEB-INF/tiles-config/tiles.xml"});
    tilesConfigurer.setCheckRefresh(true);
    return tilesConfigurer;
  }

  /**
   * Configure ViewResolvers to deliver preferred views.
   */
  @Override
  public void configureViewResolvers(ViewResolverRegistry registry) {
    TilesViewResolver viewResolver = new TilesViewResolver();
    registry.viewResolver(viewResolver);
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new PageRequestInterceptor());
  }

}
