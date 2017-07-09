/**
 * 
 */
package it.tsc.blockchain;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicLong;

import com.google.common.hash.Hashing;

/**
 * @author astraservice This class implements a block in a chain transaction
 */
public class Block {
  private AtomicLong index;
  private String previousHash;
  private AtomicLong timestamp = new AtomicLong(0);
  private String jsonData;
  private String hash;

  private static final AtomicLong LAST_TIME_MS = new AtomicLong();

  /**
   * Block constructor for blockchain
   */
  public Block(String previousHash, String jsonData, long index) {
    super();
    this.previousHash = previousHash;
    this.jsonData = jsonData;
    this.timestamp.set(uniqueCurrentNanoTime());
    this.index = new AtomicLong(index);
    this.hash = calculateHash(this);
  }

  public AtomicLong getIndex() {
    return index;
  }

  public String getPreviousHash() {
    return previousHash;
  }


  public AtomicLong getTimestamp() {
    return timestamp;
  }

  public String getJsonData() {
    return jsonData;
  }

  public String getHash() {
    return hash;
  }

  /**
   * generate Hash of Block
   * 
   * @return
   */
  public String calculateHash(Block block) {
    String generateHash = block.getIndex().toString().concat(block.getPreviousHash())
        .concat(block.getTimestamp().toString()).concat(block.getJsonData());
    hash = Hashing.sha256().hashString(generateHash, StandardCharsets.UTF_8).toString();
    return hash;
  }

  /**
   * MultiThread version of timestamp Unicity is given by AtomicLong
   * 
   * @return
   */
  private static long uniqueCurrentNanoTime() {
    long now = System.nanoTime();
    while (true) {
      long lastTime = LAST_TIME_MS.get();
      if (lastTime >= now) {
        now = lastTime + 1;
      }
      if (LAST_TIME_MS.compareAndSet(lastTime, now)) {
        return now;
      }
    }
  }

}
