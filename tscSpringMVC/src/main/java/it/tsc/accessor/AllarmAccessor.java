/**
 * 
 */
package it.tsc.accessor;

import java.util.Date;

import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;

/**
 * @author astraservice Accessor for Allarm
 */
@Accessor
public interface AllarmAccessor {
  @Query("INSERT INTO ks_tsc.tb_allarms (matricola,ab_codi,data_arrivo,evento,serial_uuid,user) VALUES (:matricola,:ab_codi,:data_arrivo,:evento,:serial_uuid,:user) IF NOT EXISTS;")
  public void insertAllarmeMatricola(@Param("matricola") String matricola,
      @Param("ab_codi") String ab_codi, @Param("data_arrivo") Date data_arrivo,
      @Param("evento") String evento, @Param("serial_uuid") String serial_uuid,
      @Param("user") String user);

  @Query("DELETE FROM ks_tsc.tb_allarms WHERE serial_uuid = :serial_uuid;")
  public void removeAllarme(@Param("serial_uuid") String serial_uuid);

  @Query("UPDATE ks_tsc.tb_allarms SET user=:user WHERE serial_uuid = :serial_uuid IF EXISTS;")
  public void updateAllarme(@Param("serial_uuid") String serial_uuid, @Param("user") String user);
}
