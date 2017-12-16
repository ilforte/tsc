package it.tsc.domain;
/**
 * 
 */

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

/**
 * @author astraservice POJO class for allarm
 */
@Entity
@Table(name = "tb_allarms", schema = "ks_tsc@cassandra_pu")
@NamedQueries(value = {
		@NamedQuery(name = Allarm.SELECT_ALL_ALLARMS, query = "SELECT a.serial_uuid,a.ab_codi,a.data_arrivo,a.evento,a.matricola FROM Allarm a"),
		@NamedQuery(name = Allarm.UPDATE_ALLARM, query = "UPDATE Allarm a SET a.user=:user WHERE a.serial_uuid=:serial_uuid") })
public class Allarm extends BaseDomain {
	public static final String SELECT_ALL_ALLARMS = "json.select.allarms";
	public static final String UPDATE_ALLARM = "json.update.allarm";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1985698665937980250L;
	@Id
	@Column
	@Expose
	String serial_uuid;

	@Column
	@Expose
	private Date data_arrivo;
	@Column
	@Expose
	private String evento;
	@Column
	@Expose
	private String ab_codi;
	@Column
	@Expose
	private String matricola;
	@Column
	@Expose
	private String tel;
	@Column
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
