Exercise: JavaFX Course Registration Form
Objective
Create a functional GUI window that allows a student to enter their name and select a course. When the "Register" button is clicked, the application should display a confirmation message.
Requirements
Layout: Use a GridPane to organize the elements.
Components:
A Label and TextField for "Student Name".
A Label and ComboBox (or TextField) for "Course Code" (e.g., CMP 269).
A Button labeled "Register".
A Label at the bottom for status messages.
Behavior: Use a Lambda expression to handle the button click. It should update the status label with: "Registration Successful for [Name] in [Course]!"
Starter Code Template
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RegistrationApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);

        // TODO: Create Labels and TextFields
        // TODO: Create the Register Button
        // TODO: Implement the Button Action using a Lambda

        Scene scene = new Scene(grid, 400, 250);
        primaryStage.setTitle("Lehman Course Registration");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


