/**
 * 
 */
package it.tsc.accessor;

import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;

import it.tsc.model.Group;

/**
 * @author astraservice Group map using @Accessor
 */
@Accessor
public interface GroupAccessor {

  @Query("SELECT * FROM ks_tsc.tb_groups;")
  Result<Group> getAllGroups();

  @Query("INSERT INTO ks_tsc.tb_groups (groupname) VALUES (:groupname) IF NOT EXISTS;")
  void addGroup(@Param("groupname") String groupname);

  @Query("DELETE FROM ks_tsc.tb_groups WHERE groupname = :groupname;")
  void removeGroup(@Param("groupname") String groupname);

  @Query("INSERT INTO ks_tsc.tb_users_groups (username,groupname) VALUES (:username,:groupname) IF NOT EXISTS;")
  void addUserToGroup(@Param("username") String username, @Param("groupname") String groupname);

  @Query("DELETE FROM ks_tsc.tb_users_groups WHERE username = :username AND groupname = :groupname;")
  void removeUserFromGroup(@Param("username") String username,
      @Param("groupname") String groupname);

}
