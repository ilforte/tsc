/**
 * 
 */
package it.tsc.model;

import java.util.List;

import com.google.gson.annotations.Expose;

/**
 * @author astraservice
 *
 */
@SuppressWarnings("rawtypes")
public class Result {
  @Expose
  private List data;

  /**
   * 
   */
  public Result() {
    // TODO Auto-generated constructor stub
  }

  @SuppressWarnings("rawtypes")
  public List getData() {
    return data;
  }

  public void setData(List data) {
    this.data = data;
  }

}
