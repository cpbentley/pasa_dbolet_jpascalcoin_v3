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
public class TestPendingOperations extends TestManualClientAbstract {

   public static void main(String[] args) {

      TestPendingOperations c = new TestPendingOperations();

      c.getBlocksInThePast();
   }

   private void getBlocksInThePast() {

      PascalCoinClient client = getClientLocal();

      int count = client.getPendingsCount();

      System.out.println("getPendingsCount=" + count);

      List<Operation> pendings = client.getPendings();
      
      int counter = 0;
      for (Operation pending : pendings) {
         System.out.println(counter + " " + pending.getAccount() + " "+ pending.getTypeDescriptor());
         counter++;
      }
   }

}
