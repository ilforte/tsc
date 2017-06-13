/**
 * 
 */
package it.tsc.test.password;

import org.junit.Test;

/**
 * @author astraservice
 *
 */
public class PasswordTest {

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
  }

}
