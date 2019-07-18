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
public class TestManualClientAccountOperations extends TestManualClientAbstract {

   public static void main(String[] args) {

      TestManualClientAccountOperations c = new TestManualClientAccountOperations();

      //c.testAccount0Operations();

      c.testAccount604653Operations();
   }

   private void testAccount604653Operations() {

      PascalCoinClient client = getClientLocal();

      //Depth to search on blocks where this account has been affected. Allowed to use deep as a param name too.
      Integer depth = 5000;
      Integer startBlock = 0;
      List<Operation> list = client.getAccountOperations(604653, startBlock, depth, 0, 5000);

      System.out.println("size=" + list.size());
      for (Operation op : list) {
         sysoutOperation(op);
      }

      list = client.getAccountOperations(604653, startBlock, depth, 10, 20);

      System.out.println("size=" + list.size());
      for (Operation op : list) {
         sysoutOperation(op);
      }

      startBlock = 202584;
      list = client.getAccountOperations(604653, startBlock, depth, 0, 5000);

      System.out.println("size=" + list.size());
      for (Operation op : list) {
         sysoutOperation(op);
      }
   }

   private void testAccount0Operations() {

      PascalCoinClient client = getClientLocal();

      //Depth to search on blocks where this account has been affected. Allowed to use deep as a param name too.
      Integer depth = 10;
      Integer startBlock = 0;
      List<Operation> list = client.getAccountOperations(0, startBlock, depth, 0, 100);

      System.out.println("size=" + list.size());
      for (Operation op : list) {
         sysoutOperation(op);
      }

      depth = 100;
      list = client.getAccountOperations(0, startBlock, depth, 0, 5);

      System.out.println("size=" + list.size());
      for (Operation op : list) {
         sysoutOperation(op);
      }

      startBlock = 283625;
      depth = 1;
      list = client.getAccountOperations(0, startBlock, depth, 0, 200);

      System.out.println("size=" + list.size());
      for (Operation op : list) {
         sysoutOperation(op);
      }

      startBlock = 333738;
      depth = 10;
      list = client.getAccountOperations(0, startBlock, depth, 0, 200);

      System.out.println("size=" + list.size());
      for (Operation op : list) {
         sysoutOperation(op);
      }
   }

}
