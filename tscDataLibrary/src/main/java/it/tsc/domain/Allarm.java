package it.tsc.domain;
/**
 * 
 */


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author astraservice
 *
 */
@Entity
@Table(name = "tb_allarms", schema = "ks_tsc@cassandra_pu")
public class Allarm extends BaseDomain{
	
  @Id
  @Column
  String serial_uuid;

	public String getSerial_uuid() {
		return serial_uuid;
	}
	
	public void setSerial_uuid(String serial_uuid) {
		this.serial_uuid = serial_uuid;
	}

}
