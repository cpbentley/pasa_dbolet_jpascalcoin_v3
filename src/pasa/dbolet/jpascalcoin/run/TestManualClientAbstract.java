package pasa.dbolet.jpascalcoin.run;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.github.davidbolet.jpascalcoin.api.client.PascalCoinClient;
import com.github.davidbolet.jpascalcoin.api.client.PascalCoinClientImpl;
import com.github.davidbolet.jpascalcoin.api.constants.PascalCoinConstants;
import com.github.davidbolet.jpascalcoin.api.model.Block;
import com.github.davidbolet.jpascalcoin.api.model.Operation;

/**
 * Just a manual class to play around with {@link PascalCoinClient}
 * 
 * @author Charles Bentley
 *
 */
public abstract class TestManualClientAbstract {

   protected SimpleDateFormat df   = new SimpleDateFormat("YYYY/MM/dd - HH:mm:ss");

   private short              port = PascalCoinConstants.DEFAULT_MAINNET_RPC_PORT;

   public PascalCoinClient getClient() {
      PascalCoinClient client = new PascalCoinClientImpl("192.168.0.12", port);

      return client;
   }

   public PascalCoinClient getClientLocal() {
      PascalCoinClient client = new PascalCoinClientImpl("127.0.0.1", port);

      return client;
   }

   public short getPort() {
      return port;
   }

   public void setPort(short port) {
      this.port = port;
   }

   public void sysoutBlock(Block b) {
      System.out.print("block " + b.getBlock() + "#ops=" + b.getOperationCount());
      long unixTime = b.getTimestamp();
      Date timeDate = new Date(unixTime * 1000);
      System.out.print("\t" + df.format(timeDate));
      System.out.print("fee=" + b.getFee());
      System.out.print(" " + b.getPayload());
      System.out.println("");
   }

   public void sysoutOperation(Operation op) {
      System.out.print("op " + op.getBlock() + "#" + op.getOperationBlock());
      long unixTime = op.getTime();
      Date timeDate = new Date(unixTime * 1000);
      System.out.print("\t" + df.format(timeDate));
      System.out.print(" " + op.getAccount());
      System.out.print(" " + op.getBalance());
      System.out.println("");
   }
}
