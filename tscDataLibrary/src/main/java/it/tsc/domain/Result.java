/**
 * 
 */
package it.tsc.domain;

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

  public List getData() {
    return data;
  }

  public void setData(List data) {
    this.data = data;
  }

}
