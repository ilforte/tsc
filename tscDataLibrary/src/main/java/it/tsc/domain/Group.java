/**
 * 
 */
package it.tsc.domain;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import com.google.gson.annotations.Expose;

/**
 * @author astraservice
 *
 */
@Table(keyspace = "ks_tsc", name = "tb_groups", readConsistency = "QUORUM",
    writeConsistency = "QUORUM", caseSensitiveKeyspace = false, caseSensitiveTable = false)
public class Group {

  @PartitionKey
  @Column(name = "groupName")
  @Expose
  private String groupName;

  /**
   * 
   */
  public Group() {
    super();
  }

  public Group(String groupName) {
    super();
    this.groupName = groupName;
  }

  public String getGroupName() {
    return groupName;
  }

  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Group) {
      Group toCompare = (Group) obj;
      return this.groupName.equals(toCompare.getGroupName());
    }
    return false;
  }

  @Override
  public int hashCode() {
    return groupName.hashCode();
  }

}
