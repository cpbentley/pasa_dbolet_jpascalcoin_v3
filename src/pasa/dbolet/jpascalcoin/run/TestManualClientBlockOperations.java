package pasa.dbolet.jpascalcoin.run;

import java.util.List;

import com.github.davidbolet.jpascalcoin.api.client.PascalCoinClient;
import com.github.davidbolet.jpascalcoin.api.model.Operation;

/**
 * Just a manual class to play around with {@link PascalCoinClient}
 * 
 * @author Charles Bentley
 *
 */
public class TestManualClientBlockOperations extends TestManualClientAbstract {


   public static void main(String[] args) {
      
      TestManualClientBlockOperations c = new TestManualClientBlockOperations();
      
      c.getBlockOperations();
   }

   private void getBlockOperations() {

      PascalCoinClient client = getClientLocal();
      
      List<Operation> list = client.getBlockOperations(333865, 0, 100);
      
      System.out.println("size="+list.size());
      for (Operation op : list) {
         System.out.println("op "+ op.getBlock() + " #" + op.getOperationBlock());
      }
   }

  
}
