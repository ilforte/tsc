/**
 * 
 */
package it.tsc.model;

import com.google.gson.annotations.Expose;

/**
 * @author astraservice Anagrafica Utente
 */
public class Anagrafica {
  @Expose
  private String ab_codi;
  @Expose
  private String nominativo;

  /**
   * 
   */
  public Anagrafica() {

  }

  public String getAb_codi() {
    return ab_codi;
  }

  public void setAb_codi(String ab_codi) {
    this.ab_codi = ab_codi;
  }

  public String getNominativo() {
    return nominativo;
  }

  public void setNominativo(String nominativo) {
    this.nominativo = nominativo;
  }

}
