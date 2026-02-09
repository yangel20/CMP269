package Exercise1;

public class MealPlan extends PaymentMethod {

   private String status = "Pending";

   public MealPlan(String accountHolder, double balance) {
      super(accountHolder, balance);
      validateAccount();
   }

   @Override
   public void validateAccount() {
      if (balance < 0) {
         System.out.println("Balance can't be negative. Setting to 0.");
         balance = 0;
      }
   }

   @Override
   public void processPayment(double amount) {
      if (amount <= 0) {
         status = "Invalid Amount";
         System.out.println("Transaction Declined.");
         return;
      }

      if (amount > balance) {
         status = "Declined";
         System.out.println("Transaction Declined.");
      } else {
         balance -= amount;
         PaymentMethod.totalTransactions++;
         status = "Approved";
      }
   }

   @Override
   public String getPaymentStatus() {
      return "MealPlan [" + accountHolder + "] Status: " + status + ", Balance: " + balance;
   }
}
