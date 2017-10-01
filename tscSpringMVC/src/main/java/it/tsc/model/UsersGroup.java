/**
 * 
 */
package it.tsc.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author astraservice
 *
 */
public class UsersGroup {
  Map<String, Set<Group>> items = new HashMap<String, Set<Group>>();

  public void addItem(String username, String groupname) {
    if (items.containsKey(username)) {
      if (!items.get(username).contains(new Group(groupname))) {
        items.get(username).add(new Group(groupname));
      }
    } else {
      Set<Group> groups = new HashSet<Group>();
      groups.add(new Group(groupname));
      items.put(username, groups);
    }
  }

  public void removeItem(String username, String groupname) {
    if (items != null && items.get(username) != null) {
      if (items.get(username).size() == 1) {
        /**
         * remove entire item
         */
        items.remove(username);
      } else if (items.get(username).size() > 1) {
        items.get(username).remove(new Group(groupname));
      }
    }
  }

  @Override
  public String toString() {
    return "UsersGroup [items=" + items + "]";
  }
}
