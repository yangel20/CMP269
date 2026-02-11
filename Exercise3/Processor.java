package Exercise3;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Processor {
   public static void main (String[] args) {
      
      try ( 
         Scanner scanner = new Scanner(new File("Exercise3/students.txt"));
         PrintWriter writer = new PrintWriter("Exercise3/grades_report.txt");
      ) {
         while (scanner.hasNextLine()) {
            String student = scanner.nextLine();

            try {

               String [] data = student.split(" ");
   
               String name = data[0];
               int grade1 = Integer.parseInt(data[1]);
               int grade2 = Integer.parseInt(data[2]);
               int grade3 = Integer.parseInt(data[3]);
   
               int sum = grade1 + grade2 + grade3;
   
               double average = sum / 3.0;

               if (average < 60 ) { 
                  throw new LowGradeException("Student: " + name + " | " + "Average: " + average + " | WARNING: below average");
               }
   
               // System.out.println( "student = " + student );
               // System.out.println( "name = " + name );
               // System.out.println( "grade1 = " + grade1 );
               // System.out.println( "grade2 = " + grade2 );
               // System.out.println( "grade3 = " + grade3 );
   
   
   
               //  Student: [Name] | Average: [Value].
               writer.println("Student: " + name + " | " + "Average: " + average + ".");
            } catch (LowGradeException e) {
               writer.println(e.getMessage());
            } catch (NumberFormatException e) {
               System.out.println("Missing number or incorrect format. Data = " + student);
            }
         }
         scanner.close();
         // writer.close();
      } catch (FileNotFoundException e) {
         System.out.println("File not found!");
      }
   }
}
