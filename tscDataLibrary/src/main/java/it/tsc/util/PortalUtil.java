/**
 * 
 */
package it.tsc.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author astraservice Portal Utility class
 */
public class PortalUtil {

  /**
   * 
   */
  public PortalUtil() {
    // TODO Auto-generated constructor stub
  }

  /**
   * generate UUID algoritm
   * 
   * @return
   */
  public static String generateUUID() {
    SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMddHH:mm:ss.SSS");// dd/MM/yyyy
    Date now = new Date();
    String strDate = sdfDate.format(now);
    int randomNum = ThreadLocalRandom.current().nextInt(0, Integer.MAX_VALUE - 1);
    return strDate + "_" + Integer.toHexString(randomNum);
  }
}
