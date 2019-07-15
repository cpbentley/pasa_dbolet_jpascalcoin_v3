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
public class ManualTestsBentley {

   public static PascalCoinClient getClient() {
      PascalCoinClient client = new PascalCoinClientImpl("192.168.0.12", PascalCoinConstants.DEFAULT_MAINNET_RPC_PORT);

      return client;
   }

   public static PascalCoinClient getClientLocal() {
      PascalCoinClient client = new PascalCoinClientImpl("127.0.0.1", PascalCoinConstants.DEFAULT_MAINNET_RPC_PORT);

      return client;
   }

   public static void main(String[] args) {
      //testDonate();
      //testGet();
      //testGetWalletAccounts_0_100();

      //      testGetWalletPubKey();
      //      testGetAccount();
      //testGetAllAccounts();

      //testFindAccount();
      //testRichList();

      //testAccountForSale();

      //testBuyAccount();
      //testLast100Blocks();
      
      testFindName();
      
      //testGetAccount(101181);
      //testGetWalletAccountOwned();

      //testGetWalletAccount5(); //fails... public key not in wallet
   }

   public static void testAccountForSale() {
      PascalCoinClient client = getClientLocal();
      String name = null;
      Integer type = null;
      Boolean exact = true;
      Boolean listedForSale = true;
      Double minBalance = null;
      Double maxBalance = null;
      Integer start = 0;
      Integer max = 1000;
      long time = System.currentTimeMillis();
      List<Account> lista = client.findAccounts(name, exact, type, listedForSale, minBalance, maxBalance, start, max);
      for (Iterator iterator = lista.iterator(); iterator.hasNext();) {
         Account ac0 = (Account) iterator.next();
         System.out.println(ac0.getAccount() + " : " + ac0.getPrice());
      }
      long diff = System.currentTimeMillis() - time;
      System.out.println("Done in " + (diff / 1000) + " seconds");
   }

   public static void testBuyAccount() {
      PascalCoinClient client = getClientLocal();

      List<PublicKey> list = client.getWalletPubKeys(0, 50);
      for (Iterator iterator = list.iterator(); iterator.hasNext();) {
         PublicKey publicKey = (PublicKey) iterator.next();
         if (publicKey.getName().equals("Pascal Java Swing")) {
            String newEncPubKey = publicKey.getEncPubKey();
            String newB58PubKey = null; //only one is needed
            Integer sellerAccount = 112800;
            Double price = 0.1000;
            Double amount = 0.1001;
            Double fee = 0.0001;
            Integer buyerAccount = 499200;
            Integer accountToBuy = 483014;
            byte[] payload = null;
            PayLoadEncryptionMethod encryptMethod = PayLoadEncryptionMethod.NONE;
            String pwd = null;
            Operation op = client.buyAccount(buyerAccount, accountToBuy, price, sellerAccount, newB58PubKey, newEncPubKey, amount, fee, payload, encryptMethod, pwd);

            if (op != null) {
               System.out.println("Success:" + op.getTypeDescriptor());
            } else {
               System.out.println("Failure");
            }
         }
      }

   }

   public static void testDonate() {
      PascalCoinClient client = new PascalCoinClientImpl("192.168.1.12", PascalCoinConstants.DEFAULT_MAINNET_RPC_PORT);
      client.unlock("angband9");

      Integer accountSender = new Integer(676300);
      Integer accountTarget = new Integer(381309);
      Double amount = new Double(1.0);
      Double fee = new Double(0.0);
      String payload = "nice work. sent with jpascalcoin";
      Operation op = client.sendTo(accountSender, accountTarget, amount, fee, payload.getBytes(), PayLoadEncryptionMethod.NONE, "");

      int accountOp = op.getAccount();
      double balanceOp = op.getBalance().doubleValue();
      double amountOp = op.getAmount().doubleValue();

      System.out.println("Sent to " + accountOp + " balanceOP=" + balanceOp + " amountOp=" + amountOp);
   }

   public static void testFindAccount() {
      PascalCoinClient client = getClientLocal();
      String name = null;
      Integer type = null;

      Account[] ar = new Account[100000];
      //read account by batches of 1000
      Integer start = 0;
      Integer max = 1000;
      int arCount = 0;
      long time = System.currentTimeMillis();

      for (int i = 0; i < 100; i++) {
         List<Account> accs = client.findAccounts(name, type, start, max);
         Iterator it = accs.iterator();
         while (it.hasNext()) {
            Account ac0 = (Account) it.next();
            ar[arCount] = ac0;
            arCount++;
         }
         start += max;
      }

      for (int i = 0; i < ar.length; i++) {
         if (ar[i] == null)
            break;
         else {
            Account ac0 = ar[i];
            System.out.println(ac0.getAccount() + " \t " + ac0.getBalance() + " \t" + ac0.getEncPubkey());
         }
      }
      long diff = System.currentTimeMillis() - time;

      System.out.println("" + (diff / 1000) + " seconds for " + arCount + " accounts");

   }

   public static void testFindName() {
      PascalCoinClient client = getClientLocal();
      //search must be 3 characters at least for the 
      String name = "pasc";
      Boolean exact = false;
      Integer type = null;
      Integer start = 0;
      Integer max = 10;
      System.out.println("List<Account> findAccounts(String name, Integer type, Integer start, Integer max);");
      List<Account> list = client.findAccounts(name, type, start, max);
      for (Iterator iterator = list.iterator(); iterator.hasNext();) {
         Account ac0 = (Account) iterator.next();
         System.out.println(ac0.getAccount() + " : " + ac0.getName());
      }

      System.out.println("List<Account> findAccounts(String name, Boolean exact, Integer type, Boolean listed, Double minBalance, Double maxBalance, Integer start, Integer max);");
      list = client.findAccounts(name, exact, type, null, null, null, 0, 10000);
      for (Iterator iterator = list.iterator(); iterator.hasNext();) {
         Account ac0 = (Account) iterator.next();
         System.out.println(ac0.getAccount() + " : " + ac0.getName());
      }
   }

   public static void testGet() {
      //uses the default 
      PascalCoinClient client = new PascalCoinClientImpl("192.168.0.12", PascalCoinConstants.DEFAULT_MAINNET_RPC_PORT);
      client.unlock("angband9");
      Integer accountValue = new Integer(676300);
      Account account = client.getAccount(accountValue);
      System.out.println("Account's balance: PASC=" + account.getBalance());
   }

   public static void testGetAccount() {
      PascalCoinClient client = getClient();
      Account ac0 = client.getAccount(0);
      System.out.println(ac0.getAccount() + " : " + ac0.getBalance());

      Account ac2 = client.getAccount(345678);
      System.out.println(ac2.getAccount() + " : " + ac2.getBalance());

   }

   public static void testGetAccount(int account) {
      PascalCoinClient client = getClientLocal();
      Account ac0 = client.getAccount(account);
      System.out.println(ac0.getAccount() + " : " + ac0.getBalance());
      System.out.println(ac0.getnOperation() + " : " + ac0.getPrice());
      String encPubKey = ac0.getEncPubkey();
      PublicKey pk = client.decodePubKey(encPubKey, null);
      System.out.println(pk.getBase58PubKey());

   }

   public static void testGetAllAccounts() {
      PascalCoinClient client = getClientLocal();

      long time = System.currentTimeMillis();
      int ic = 0;
      int last = 1000;
      Account[] array = new Account[last];
      for (int i = 0; i < last; i++) {
         Account ac0 = client.getAccount(i);
         System.out.println(ac0.getAccount() + " : " + ac0.getBalance());
         array[i] = ac0;
      }

      long diff = System.currentTimeMillis() - time;

      System.out.println("" + (diff / 1000) + " seconds for " + last + " accounts");

      File f = new File("D:\\jpascaldb.acc");
      try {
         FileOutputStream fos = new FileOutputStream(f);
         ObjectOutputStream ous = new ObjectOutputStream(fos);
         ous.writeInt(array.length);
         for (int i = 0; i < array.length; i++) {
            ous.writeObject(array[i]);
         }
         ous.close();
      } catch (Exception e) {
         e.printStackTrace();
      }

      try {
         FileInputStream fis = new FileInputStream(f);
         ObjectInputStream oss = new ObjectInputStream(fis);
         int num = oss.readInt();
         Account[] ar = new Account[num];
         for (int i = 0; i < ar.length; i++) {
            ar[i] = (Account) oss.readObject();
         }
         oss.close();
         for (int i = 0; i < ar.length; i++) {
            Account ac0 = ar[i];
            System.out.println(ac0.getAccount() + " \t " + ac0.getBalance() + " \t" + ac0.getEncPubkey());
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public static void testGetWalletAccount5() {
      PascalCoinClient client = getClientLocal();
      String encPubKey = null; //optional
      String b58PubKey = "JJj2GZDmBCwbQuFLumucGMqWALmqpNxuYDEpPYi6aq4y4gvdFv1JLJYGgQyt5oUa1sgyUoAK8m7adARBa9JfGuKRtJQsiegEWQ41WcsyQAZ9iUcnFRVgKe89mqmbuU8hPF7ewisP49HdJCpfHxiwiz95JGWoBP3XdSf4TvBQShjFeVuMEB6pXCMQCfcnG1uBi"; //optional
      Integer start = null;
      Integer max = null;
      List<Account> list = client.getWalletAccounts(encPubKey, b58PubKey, start, max);
      Iterator it = list.iterator();
      while (it.hasNext()) {
         Account ac = (Account) it.next();
         Integer acc = ac.getAccount();
         Double balance = ac.getBalance();
         String name = ac.getName();
         Integer nOp = ac.getnOperation();
         System.out.println(acc + " " + balance + " " + name + " " + nOp);
      }
   }

   public static void testGetWalletAccountOwned() {
      PascalCoinClient client = getClientLocal();

      client.getPendings();
      client.getWalletPubKeys(null, null);
      String encPubKey = null; //optional
      //String b58PubKey = "3GhhbonjG8WJz8ENu8wMYYENodeB6UU2NeCsrNsnQdsqC1sopAzvdhYHUiXC3Xnu4EStx5y927UEpeKisWusPCP3AJ2H83n3Su5MJH"; //optional
      String b58PubKey = "3GhhborHzhUGi35CJCtRMVFhWGE9HB5DL8UPNL9sSRUqmuiP2QQrnUGdroZWok4LNvNW2aJuTGRvVbnp65WQq7gHSo4xVV2xVsTiin"; //optional
      Integer start = null;
      Integer max = null;
      List<Account> list = client.getWalletAccounts(encPubKey, b58PubKey, start, max);
      Iterator it = list.iterator();
      while (it.hasNext()) {
         Account ac = (Account) it.next();
         Integer acc = ac.getAccount();
         Double balance = ac.getBalance();
         String name = ac.getName();
         Integer nOp = ac.getnOperation();
         System.out.println(acc + " " + balance + " " + name + " " + nOp);
      }
   }

   public static void testGetWalletAccounts_0_100() {
      PascalCoinClient client = getClient();
      String encPubKey = null; //optional
      String b58PubKey = null; //optional
      Integer start = 0;
      Integer max = 110;
      //it gets account from the wallet
      List<Account> list = client.getWalletAccounts(encPubKey, b58PubKey, start, max);
      Iterator it = list.iterator();
      while (it.hasNext()) {
         Account ac = (Account) it.next();
         Integer acc = ac.getAccount();
         Double balance = ac.getBalance();
         String name = ac.getName();
         Integer nOp = ac.getnOperation();

         System.out.println(acc + " " + balance + " " + name + " " + nOp);

      }
   }

   public static void testGetWalletPubKey() {

      PascalCoinClient client = getClient();
      Integer start = 0;
      Integer max = 110;
      List<PublicKey> list = client.getWalletPubKeys(start, max);
      Iterator it = list.iterator();
      while (it.hasNext()) {
         PublicKey pk = (PublicKey) it.next();
         String encodedPk = pk.getEncPubKey();
         String name = pk.getName();
         String base58 = pk.getBase58PubKey();
         String x = pk.getX();
         String y = pk.getY();
         System.out.println(base58);
         System.out.println(" encoded=" + encodedPk + " name=" + name);
         System.out.println("x=" + x + " y=" + y);

      }

   }

   public static void testLast100Blocks() {

      PascalCoinClient client = getClientLocal();

      List<Block> list = client.getBlocks(100, null, null);
      for (Iterator iterator = list.iterator(); iterator.hasNext();) {
         Block ac0 = (Block) iterator.next();
         System.out.println(ac0.getBlock() + " : #operations=" + ac0.getOperationCount());
      }
   }

   public static void testRichList() {
      PascalCoinClient client = getClientLocal();

      String name = null;
      Integer type = null;
      Boolean exact = true;
      Boolean listedForSale = false;
      Double minBalance = 1000.0;
      Double maxBalance = 2000.0;
      Integer start = 0;
      Integer max = 1000;
      long time = System.currentTimeMillis();

      List<Account> lista = client.findAccounts(name, exact, type, listedForSale, minBalance, maxBalance, start, max);

      for (Iterator iterator = lista.iterator(); iterator.hasNext();) {
         Account ac0 = (Account) iterator.next();
         System.out.println(ac0.getAccount() + " : " + ac0.getBalance());
      }

      long diff = System.currentTimeMillis() - time;
      System.out.println("Done in " + (diff / 1000) + " seconds");

   }
}
