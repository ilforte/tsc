/**
 * 
 */
package it.tsc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author astraservice
 *
 */
public class PageRequestInterceptor implements HandlerInterceptor {
  private static Logger logger = LoggerFactory.getLogger(PageRequestInterceptor.class);

  /*
   * (non-Javadoc)
   * 
   * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.
   * HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object,
   * java.lang.Exception)
   */
  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
      Object handler, Exception ex) throws Exception {
    String referrer = request.getHeader("referer");
    logger.debug("Request Completed handler:{}", handler, referrer);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.
   * HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object,
   * org.springframework.web.servlet.ModelAndView)
   */
  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView model) throws Exception {
    String referrer = request.getHeader("referer");
    logger.debug("method executed handler:{}", handler, referrer);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.
   * HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
   */
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    String referrer = request.getHeader("referer");
    logger.debug("Before Method Execution handler:{} referrer:{}", handler, referrer);
    return true;
  }

}
