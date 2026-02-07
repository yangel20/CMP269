package Exercise1;

public abstract class PaymentMethod implements Payable {

   protected String accountHolder;
   protected double balance;

   public PaymentMethod(String accountHolder, double balance) {
      this.accountHolder = accountHolder;
      this.balance = balance;
   }

   // Tracks how many payments have been processed across the entire system.
   public static int totalTransactions = 0;

   public abstract void validateAccount();
}
