package it.tsc.config;

import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import it.tsc.interceptor.PageRequestInterceptor;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"it.tsc.service.impl", "it.tsc.dao.impl",
    "it.tsc.authentication.provider", "it.tsc.controller"})
@Import({ServiceConfig.class, SecurityConfig.class})
public class WebAppConfig extends WebMvcConfigurerAdapter {

  public WebAppConfig() {

  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    registry.addResourceHandler("/resources/css/**").addResourceLocations("/resources/css/")
        .setCachePeriod(31556926);
    registry.addResourceHandler("/resources/img/**").addResourceLocations("/resources/img/")
        .setCachePeriod(31556926);
    registry.addResourceHandler("/resources/js/**").addResourceLocations("/resources/js/")
        .setCachePeriod(31556926);
  }

  @Override
  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
    super.configureDefaultServletHandling(configurer);
    configurer.enable();
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
    /**
     * i18n
     */
    registry.addInterceptor(localeChangeInterceptor());
  }

  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    GsonHttpMessageConverter msgConverter = new GsonHttpMessageConverter();
    Gson gson =
        new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
    msgConverter.setGson(gson);
    converters.add(msgConverter);
  }

  /**
   * Resolver
   * 
   * @return
   */
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
   * i18n message
   * 
   * @return
   */
  @Bean
  public MessageSource messageSource() {
    ReloadableResourceBundleMessageSource messageSource =
        new ReloadableResourceBundleMessageSource();
    messageSource.setBasename("/WEB-INF/classes/messages");
    return messageSource;
  }

  @Bean
  public LocaleChangeInterceptor localeChangeInterceptor() {
    LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
    localeChangeInterceptor.setParamName("locale");
    return localeChangeInterceptor;
  }

  @Bean(name = "localeResolver")
  public LocaleResolver getLocaleResolver() {
    return new CookieLocaleResolver();
  }

}
