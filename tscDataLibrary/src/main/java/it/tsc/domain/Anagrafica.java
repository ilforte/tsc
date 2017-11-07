/**
 * 
 */
package it.tsc.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.gson.annotations.Expose;

/**
 * @author astraservice Anagrafica Utente
 */
@Entity
@Table(name = "tb_anagrafica", schema = "ks_tsc@cassandra_pu")
@NamedQueries(value = {
		@NamedQuery(name = Anagrafica.SELECT_ALL_ANAGRAFICA, query = "SELECT a FROM Anagrafica a WHERE a.ab_codi = :ab_codi") })
public class Anagrafica {
	public static final String SELECT_ALL_ANAGRAFICA = "json.select.anagrafica";
	@Id
	@Column
	@Expose
	private String ab_codi;
	@Column
	@Expose
	private String nominativo;
	@Transient
	@Expose
	private Integer altezza;
	@Transient
	@Expose
	private Double peso;

	/**
	 * 
	 */
	public Anagrafica() {

	}

	public Integer getAltezza() {
		return altezza;
	}

	public void setAltezza(Integer altezza) {
		this.altezza = altezza;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
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
