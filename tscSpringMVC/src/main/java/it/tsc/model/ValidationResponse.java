/**
 * 
 */
package it.tsc.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import com.google.gson.annotations.Expose;

/**
 * @author astraservice
 *
 */
public class ValidationResponse {
  @Expose
  private String status;
  @Expose
  private List<ErrorMessage> result;
  @Expose
  private String resultMessage;

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public List<ErrorMessage> getResult() {
    return result;
  }

  /**
   * create result message list
   * 
   * @param <T>
   * 
   * @param bindingResult
   */
  public <T> void setBindingResult(Set<ConstraintViolation<T>> errors) {
    result = new ArrayList<ErrorMessage>();
    for (ConstraintViolation<T> error : errors) {
      ErrorMessage errorMessage = new ErrorMessage();
      errorMessage.setFieldName(error.getPropertyPath().toString());
      errorMessage.setMessage(error.getMessage());
      result.add(errorMessage);
    }
  }

  public String getResultMessage() {
    return resultMessage;
  }

  public void setResultMessage(String resultMessage) {
    this.resultMessage = resultMessage;
  }

}
