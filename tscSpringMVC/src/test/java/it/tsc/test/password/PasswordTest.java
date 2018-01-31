/**
 * 
 */
package it.tsc.test.password;

import org.junit.Test;

import it.tsc.web.parallel.WebParallelTest;


/**
 * @author astraservice
 *
 */
public class PasswordTest extends WebParallelTest {

  /**
   * 
   */
  public PasswordTest() {
    // TODO Auto-generated constructor stub
  }

  @Test
  public void testPassword() {
    org.springframework.security.crypto.password.PasswordEncoder encoder =
        new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();

    for (int i = 0; i < 5; i++) {
      // "123456" - plain text - user input from user interface
      String passwd = encoder.encode("123456");

      // passwd - password from database
      System.out.println(passwd); // print hash

      // true for all 5 iteration
      System.out.println(encoder.matches("123456", passwd));
    }

    String passwd = encoder.encode("telesoccorsoInFamiglia01$");
    System.out.println("tsc pwd: " + passwd); // print hash
    passwd = encoder.encode("cxf");
    System.out.println("cxf pwd: " + passwd); // print hash
    String europeAssistance = encoder.encode("europeAssistance");
    System.out.println("europeAssistance: " + europeAssistance); // print hash
  }

}
