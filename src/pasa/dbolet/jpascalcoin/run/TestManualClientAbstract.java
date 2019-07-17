package pasa.dbolet.jpascalcoin.run;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.List;

import com.github.davidbolet.jpascalcoin.api.client.PascalCoinClient;
import com.github.davidbolet.jpascalcoin.api.client.PascalCoinClientImpl;
import com.github.davidbolet.jpascalcoin.api.constants.PascalCoinConstants;
import com.github.davidbolet.jpascalcoin.api.model.Account;
import com.github.davidbolet.jpascalcoin.api.model.Block;
import com.github.davidbolet.jpascalcoin.api.model.Operation;
import com.github.davidbolet.jpascalcoin.api.model.PayLoadEncryptionMethod;
import com.github.davidbolet.jpascalcoin.api.model.PublicKey;

/**
 * Just a manual class to play around with {@link PascalCoinClient}
 * 
 * @author Charles Bentley
 *
 */
public abstract class TestManualClientAbstract {

   public static PascalCoinClient getClient() {
      PascalCoinClient client = new PascalCoinClientImpl("192.168.0.12", PascalCoinConstants.DEFAULT_MAINNET_RPC_PORT);

      return client;
   }

   public static PascalCoinClient getClientLocal() {
      PascalCoinClient client = new PascalCoinClientImpl("127.0.0.1", PascalCoinConstants.DEFAULT_MAINNET_RPC_PORT);

      return client;
   }
  
}
