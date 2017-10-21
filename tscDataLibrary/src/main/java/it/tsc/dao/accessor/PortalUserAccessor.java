/**
 * 
 */
package it.tsc.dao.accessor;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;

import it.tsc.domain.PortalUser;

/**
 * @author astraservice PortalUser map using @Accessor
 */
@Accessor
public interface PortalUserAccessor {
  /**
   * add User using @Accessor
   * 
   * @param username
   * @param password
   * @param email
   * @param role
   * @return
   */
  @Query("INSERT INTO ks_tsc.tb_users (username, password, email,role) VALUES (:username, :password, :email,:role) IF NOT EXISTS;")
  public ResultSet addUser(@Param("username") String username, @Param("password") String password,
      @Param("email") String email, @Param("role") String role);

  /**
   * delete User using @Accessor
   * 
   * @param username
   * @return
   */
  @Query("DELETE FROM ks_tsc.tb_users WHERE username = :username AND role=:role IF EXISTS;")
  public ResultSet removeUser(@Param("username") String username, @Param("role") String role);

  /**
   * update User using @Accessor
   * 
   * @param username
   * @param password
   * @param email
   * @param role
   * @return
   */
  @Query("UPDATE ks_tsc.tb_users SET password=:password,email=:email WHERE username = :username AND role = :role IF EXISTS;")
  public void updateUser(@Param("username") String username, @Param("password") String password,
      @Param("email") String email, @Param("role") String role);

  /**
   * Update code for MFA access
   * 
   * @param username
   * @param keyId
   * @param base32Secret
   */
  @Query("UPDATE ks_tsc.tb_users SET keyId=:keyId,base32Secret=:base32Secret WHERE username = :username and role=:role IF EXISTS;")
  public void updateMfaUserKey(@Param("username") String username, @Param("keyId") String keyId,
      @Param("base32Secret") String base32Secret, @Param("role") String role);

  /**
   * get All Users using @Accessor
   * 
   * @return
   */
  @Query("SELECT * FROM ks_tsc.tb_users;")
  public Result<PortalUser> getAllUsers();

  /**
   * return Role giving username and password using @Accessor
   * 
   * @param username
   * @param password
   * @return
   */
  @Query("SELECT * FROM ks_tsc.tb_users WHERE username = :username ALLOW FILTERING;")
  public Result<PortalUser> getUserRoles(@Param("username") String username);

  /**
   * return single user
   * 
   * @param username
   * @return
   */
  @Query("SELECT username,role,email,password,base32Secret FROM ks_tsc.tb_users WHERE username = ? ALLOW FILTERING;")
  public Result<PortalUser> getUser(@Param("username") String username);

  /**
   * return single user (for password renew)
   * 
   * @param username
   * @param email
   * @return
   */
  @Query("SELECT username,role,email,password FROM ks_tsc.tb_users WHERE username = ? AND email=:email ALLOW FILTERING;")
  public Result<PortalUser> getUser(@Param("username") String username,
      @Param("email") String email);

}
