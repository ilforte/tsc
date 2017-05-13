/**
 * 
 */
package it.tsc.model;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

/**
 * @author astraservice
 *
 */
@Table(keyspace = "ks_tsc", name = "tb_groups", readConsistency = "QUORUM",
    writeConsistency = "QUORUM", caseSensitiveKeyspace = false, caseSensitiveTable = false)
public class Group {
  @PartitionKey
  @Column(name = "groupName")
  private String groupName;

  /**
   * 
   */
  public Group() {

  }

  public String getGroupName() {
    return groupName;
  }

  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }

}
