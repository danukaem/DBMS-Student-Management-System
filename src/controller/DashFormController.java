package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashFormController {
    public AnchorPane dashRoot;

    public void register(ActionEvent actionEvent) throws IOException {

        Parent customerRoot = FXMLLoader.load(this.getClass().getResource("/view/RegisterStudent.fxml"));
        Scene customerScene = new Scene(customerRoot);
        Stage primaryStage = (Stage) dashRoot.getScene().getWindow();
        primaryStage.setScene(customerScene);
        primaryStage.centerOnScreen();
        primaryStage.setTitle("Student Registration");
    }

    public void course(ActionEvent actionEvent) throws IOException {
        Parent customerRoot = FXMLLoader.load(this.getClass().getResource("/view/ManageCourse.fxml"));
        Scene customerScene = new Scene(customerRoot);
        Stage primaryStage = (Stage) dashRoot.getScene().getWindow();
        primaryStage.setScene(customerScene);
        primaryStage.centerOnScreen();
        primaryStage.setTitle("ManageCourse");
    }

    public void batch(ActionEvent actionEvent) throws IOException {
        Parent customerRoot = FXMLLoader.load(this.getClass().getResource("/view/ManageBatch.fxml"));
        Scene customerScene = new Scene(customerRoot);
        Stage primaryStage = (Stage) dashRoot.getScene().getWindow();
        primaryStage.setScene(customerScene);
        primaryStage.centerOnScreen();
        primaryStage.setTitle("ManageBatch");
    }

    public void manageStudent(ActionEvent actionEvent) throws IOException {
        Parent customerRoot = FXMLLoader.load(this.getClass().getResource("/view/ManagementStudentForm.fxml"));
        Scene customerScene = new Scene(customerRoot);
        Stage primaryStage = (Stage) dashRoot.getScene().getWindow();
        primaryStage.setScene(customerScene);
        primaryStage.centerOnScreen();
        primaryStage.setTitle("ManagementStudent");

    }
}
