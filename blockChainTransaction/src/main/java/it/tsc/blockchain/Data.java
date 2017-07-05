/**
 * 
 */
package it.tsc.blockchain;

import java.util.UUID;

import com.google.gson.annotations.Expose;

/**
 * @author astraservice
 *
 */
public class Data {
  @Expose
  private UUID uuid;
  @Expose
  private String message;

  /**
   * a valid block of Data for blockChain
   */
  public Data() {
    // TODO Auto-generated constructor stub
  }

  public UUID getUuid() {
    return uuid;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

}
