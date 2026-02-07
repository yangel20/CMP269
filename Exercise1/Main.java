package Exercise1;
import java.util.ArrayList;

public class Main {

   public static void main(String[] args) {

      ArrayList<Payable> paymentQueue = new ArrayList<>();
      paymentQueue.add(new CreditCard("Student cc", 100.0, 200.0));
      paymentQueue.add(new MealPlan("Student mp", 100.0));

      for (Payable p : paymentQueue) {
         p.processPayment(50.0);
         System.out.println("Status: " + p.getPaymentStatus());
      }

      System.out.println("Total Transactions: " + PaymentMethod.totalTransactions);
   }
}
