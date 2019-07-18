package pasa.dbolet.jpascalcoin.run;

import java.util.List;

import com.github.davidbolet.jpascalcoin.api.client.PascalCoinClient;
import com.github.davidbolet.jpascalcoin.api.model.Block;
import com.github.davidbolet.jpascalcoin.api.model.Operation;

/**
 * Just a manual class to play around with {@link PascalCoinClient}
 * 
 * @author Charles Bentley
 *
 */
public class TestManualClientBlocks extends TestManualClientAbstract {

   public static void main(String[] args) {

      TestManualClientBlocks c = new TestManualClientBlocks();

      c.getBlocksInThePast();
   }

   private void getBlocksInThePast() {

      PascalCoinClient client = getClientLocal();

      List<Block> list = client.getBlocks(10, null, null);

      System.out.println("size=" + list.size());
      for (Block block : list) {
         sysoutBlock(block);
      }

      list = client.getBlocks(null, 10, 10 + 4);

      System.out.println("size=" + list.size());
      for (Block block : list) {
         sysoutBlock(block);
      }

      list = client.getBlocks(null, 10 + 4, 10 + 8);

      System.out.println("size=" + list.size());
      for (Block block : list) {
         sysoutBlock(block);
      }

   }

}
