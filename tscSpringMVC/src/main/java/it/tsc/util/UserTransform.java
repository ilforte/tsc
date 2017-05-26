/**
 * 
 */
package it.tsc.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import it.tsc.model.PortalUser;

/**
 * @author astraservice
 *
 */
public class UserTransform {
  HashMap<String, PortalUser> keys = new HashMap<String, PortalUser>();

  /**
   * 
   */
  public UserTransform() {

  }

  public void addUser(String username, String role, String email) {
    if (!keys.containsKey(username)) {
      PortalUser user = new PortalUser();
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

  public List<PortalUser> getUsers() {
    List<PortalUser> users = new ArrayList<PortalUser>();
    for (Entry<String, PortalUser> entry : keys.entrySet()) {
      users.add(entry.getValue());
    }
    return users;
  }

}
