/**
 * 
 */
package it.tsc.util;

import java.sql.Timestamp;

/**
 * @author astraservice Class for time utilities
 */
public class TimeUtil {

  /**
   * 
   */
  public TimeUtil() {
    // TODO Auto-generated constructor stub
  }

  public static Timestamp getCurrentTimeStamp() {
    return new Timestamp(System.currentTimeMillis());
  }

}
