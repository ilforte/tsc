/**
 * 
 */
package it.tsc.domain;

import com.google.gson.annotations.Expose;

/**
 * @author astraservice
 *
 */
public class ErrorMessage {
  @Expose
  private String fieldName;
  @Expose
  private String message;

  /**
   * 
   */
  public ErrorMessage() {

  }

  public String getFieldName() {
    return fieldName;
  }

  public void setFieldName(String fieldName) {
    this.fieldName = fieldName;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

}
