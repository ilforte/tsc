/**
 * 
 */
package it.tsc.dao.accessor;

import com.datastax.driver.mapping.annotations.Accessor;

/**
 * @author astraservice Accessor for tsc service
 */
@Accessor
public interface TscAccessor {

  // @Query("SELECT JSON ab_codi,nominativo FROM ks_tsc.tb_anagrafica WHERE ab_codi=:ab_codi;")
  // public String getAnagrafica(@Param("ab_codi") String ab_codi);

}
