/**
 * 
 */
package it.tsc.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

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

  /**
   * return Date using UTC
   * 
   * @return
   */
  public static String getCurrentDate() {
    Calendar cSchedStartCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
    cSchedStartCal.setTimeZone(TimeZone.getTimeZone("Europe/Rome"));
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    System.out.println(sdf.format(cSchedStartCal.getTime()));
    return sdf.format(cSchedStartCal.getTime());
  }


}
