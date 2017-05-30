/**
 * 
 */
package it.tsc.model;

import java.util.Date;

import com.google.gson.annotations.Expose;

/**
 * @author astraservice POJO class for allarm
 */
public class Allarm {
  @Expose
  private Date data_arrivo;
  @Expose
  private String serial_uuid;
  @Expose
  private String evento;
  @Expose
  private String ab_codi;
  @Expose
  private String matricola;
  @Expose
  private String tel;
  @Expose
  private String user;

  /**
   * 
   */
  public Allarm() {
    // TODO Auto-generated constructor stub
  }

  public String getAb_codi() {
    return ab_codi;
  }

  public void setAb_codi(String ab_codi) {
    this.ab_codi = ab_codi;
  }

  public String getMatricola() {
    return matricola;
  }

  public void setMatricola(String matricola) {
    this.matricola = matricola;
  }

  public Date getData_arrivo() {
    return data_arrivo;
  }

  public void setData_arrivo(Date data_arrivo) {
    this.data_arrivo = data_arrivo;
  }

  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public String getSerial_uuid() {
    return serial_uuid;
  }

  public void setSerial_uuid(String serial_uuid) {
    this.serial_uuid = serial_uuid;
  }

  public String getEvento() {
    return evento;
  }

  public void setEvento(String evento) {
    this.evento = evento;
  }

}
