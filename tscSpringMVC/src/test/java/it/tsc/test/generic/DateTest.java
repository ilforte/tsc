/**
 * 
 */
package it.tsc.test.generic;

import java.text.ParseException;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.junit.Test;

import it.tsc.util.TimeUtil;
import it.tsc.web.parallel.WebParallelTest;

/**
 * @author astraservice
 *
 */
public class DateTest extends WebParallelTest {

  @Test
  public void dateTest() {
    Date date1 = new Date();
    System.out.println(date1);

    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    // or pass in a command line arg: -Duser.timezone="UTC"

    Date date2 = new Date();
    System.out.println(date2);
  }

  @Test
  public void dateTest2() throws ParseException {
    System.out.println(Calendar.getInstance().getTime());
  }

  @Test
  public void dateTest3() {
    System.out.println(TimeUtil.getCurrentDate());
    ZonedDateTime.parse(TimeUtil.getCurrentDate());
    System.out.println(Instant.parse(TimeUtil.getCurrentDate()));
  }

}
