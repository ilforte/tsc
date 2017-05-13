/**
 * 
 */
package it.tsc.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import it.tsc.model.TscUser;

/**
 * @author astraservice
 *
 */
public class UserTransform {
  HashMap<String, TscUser> keys = new HashMap<String, TscUser>();

  /**
   * 
   */
  public UserTransform() {

  }

  public void addUser(String username, String role, String email) {
    if (!keys.containsKey(username)) {
      TscUser user = new TscUser();
      user.setUsername(username);
      user.setEmail(email);
      List<String> roles = new ArrayList<String>();
      roles.add(role);
      user.setRoles(roles);
      keys.put(username, user);
    } else {
      keys.get(username).getRoles().add(role);
    }
  }

  public List<TscUser> getUsers() {
    List<TscUser> users = new ArrayList<TscUser>();
    for (Entry<String, TscUser> entry : keys.entrySet()) {
      users.add(entry.getValue());
    }
    return users;
  }

}
