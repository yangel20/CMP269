package Exercise3;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;


public class Processor {
   public static void main (String[] args) {


      // Scanner scanner = new Scanner(System.in);
      
      try ( 
         Scanner scanner = new Scanner(new File("Exercise3/students.txt"));
         PrintWriter writer = new PrintWriter("grades_report.txt");
      ) {
         while (scanner.hasNextLine()) {
            String student = scanner.nextLine();

            String [] data = student.split(" ");

            String name = data[0];
            int grade1 = Integer.parseInt(data[1]);
            int grade2 = Integer.parseInt(data[2]);
            int grade3 = Integer.parseInt(data[3]);

            int sum = grade1 + grade2 + grade3;

            float average = sum / 3;

            System.out.println( "student = " + student );
            System.out.println( "name = " + name );
            System.out.println( "grade1 = " + grade1 );
            System.out.println( "grade2 = " + grade2 );
            System.out.println( "grade3 = " + grade3 );



            //  Student: [Name] | Average: [Value].
            writer.println("Student: " + name + " | " + "Average: " + average + ".");
         }
         scanner.close();
         // writer.close();

      } catch (InputMismatchException e) {
         System.out.println("mising number");
      } catch (NumberFormatException e) {
         System.out.println("mising number");
      } catch (FileNotFoundException e) {
         System.out.println("File not found!");
      }
   }
}
