package it.tsc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
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

import it.tsc.dao.CommonDao;
import it.tsc.interceptor.PageRequestInterceptor;

@Configuration
@EnableWebMvc
@ComponentScan({"it.tsc.service.impl", "it.tsc.dao.impl", "it.tsc.security.provider",
    "it.tsc.controller"})
@Import({SecurityConfig.class})
@PropertySource(value = {"classpath:cassandra.properties"}, ignoreResourceNotFound = false)
public class AppConfig extends WebMvcConfigurerAdapter {

  @Value("${cassandra-nodes}")
  private String nodes;

  @Value("#{T(java.lang.Integer).valueOf('${cassandra-port}')}")
  private Integer port;

  @Value("${cassandra-username}")
  private String username;

  @Value("${cassandra-password}")
  private String password;

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
   * Property placeholder configurer needed to process @Value annotations
   */
  @Bean
  public static PropertySourcesPlaceholderConfigurer propertyConfigurerabstractDao() {
    return new PropertySourcesPlaceholderConfigurer();
  }

  @Bean
  public CommonDao commonDao() {
    return new CommonDao(nodes, port, username, password) {};

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
