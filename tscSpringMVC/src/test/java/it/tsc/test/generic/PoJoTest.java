/**
 * 
 */
package it.tsc.test.generic;

import org.junit.Test;

import it.tsc.domain.UsersGroup;

/**
 * @author astraservice
 *
 */
public class PoJoTest {

  @Test
  public void testUsersGroup() {
    UsersGroup ug = new UsersGroup();
    ug.addItem("matteo", "MILANO");
    ug.addItem("matteo", "NAPOLI");
    ug.addItem("matteo", "NAPOLI");
    System.out.println(ug.toString());
    ug.removeItem("matteo", "MILANO");
    System.out.println(ug.toString());
  }
}
