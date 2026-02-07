package Exercise1;

public class CreditCard extends PaymentMethod {

   private double creditLimit;

   public CreditCard(String accountHolder, double balance, double creditLimit) {
      super(accountHolder, balance);
      this.creditLimit = creditLimit;
      validateAccount();
   }

   @Override
   public void validateAccount() {
      if (creditLimit < 0) {
         creditLimit = 0;
      } 
   }

   @Override
   public void processPayment(double amount) {
      if (amount > balance + creditLimit) {
         System.out.println("Transaction Declined.");
      } else {
         balance -= amount;
         PaymentMethod.totalTransactions++;
      }
   }

   @Override
   public String getPaymentStatus() {
      return "CreditCard - Holder: " + accountHolder +
             ", Balance: " + balance +
             ", Credit Limit: " + creditLimit;
   }
}
