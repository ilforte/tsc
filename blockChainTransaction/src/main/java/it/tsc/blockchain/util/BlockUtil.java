/**
 * 
 */
package it.tsc.blockchain.util;

import java.util.concurrent.atomic.AtomicLong;

import com.google.gson.Gson;

import it.tsc.blockchain.Block;
import it.tsc.blockchain.Data;

/**
 * @author astraservice incrementBlockIndex Singleton Class
 */
public class BlockUtil {
  private static final AtomicLong lastIndex = new AtomicLong(0);
  private static volatile BlockUtil instance = null;
  private static String GENESYS_BLOCK = "genesis block";
  private static Gson gson = new Gson();

  protected BlockUtil() {
    // Exists only to defeat instantiation.
  }

  public static BlockUtil getInstance() {
    BlockUtil blockUtil = instance;
    if (blockUtil == null) {
      synchronized (BlockUtil.class) {
        blockUtil = instance;
        if (blockUtil == null) {
          blockUtil = instance = new BlockUtil();
        }
      }
    }
    return blockUtil;
  }

  /**
   * generate First Bloch of BlockChain
   * 
   * @param jsonData
   * @return
   */
  public static Block generateFirstBlockChain(Data data) {
    data.setMessage(GENESYS_BLOCK);
    return new Block("", gson.toJson(data), getFirstindex());
  }

  /**
   * insert valid Block to chain
   * 
   * @param previousBlock
   * @param jsonData
   * @return
   */
  public static Block insertBlockChain(Block previousBlock, Data data) {
    return new Block(previousBlock.getPreviousHash(), gson.toJson(data), incrementLastindex());
  }

  /**
   * get First Index
   */
  private static long getFirstindex() {
    lastIndex.set(0L);
    return lastIndex.get();
  }

  /**
   * increment Last Index
   * 
   * @return
   */
  private static long incrementLastindex() {
    return lastIndex.addAndGet(1L);
  }

  /**
   * check if block is valid
   * 
   * @param newBlock
   * @param previousBlock
   * @return
   */
  private static boolean isValidBlock(Block newBlock, Block previousBlock) {
    if (previousBlock.getIndex().get() + 1 != newBlock.getIndex().get()) {
      return false;
    } else if (previousBlock.getHash() != newBlock.getPreviousHash()) {
      return false;
    } else if (newBlock.calculateHash(newBlock) != newBlock.getHash()) {
      return false;
    } else {
      return true;
    }
  }

}
