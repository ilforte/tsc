/**
 * 
 */
package it.tsc.dao.accessor;

import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;

import it.tsc.model.Anagrafica;

/**
 * @author astraservice Accessor for tsc service
 */
@Accessor
public interface TscAccessor {

  @Query("SELECT JSON ab_codi,nominativo FROM ks_tsc.tb_anagrafica;")
  public Result<Anagrafica> getAnagrafica(@Param("ab_codi") String ab_codi);

}
