package controller;

import Business.BOFactory;
import Business.Custom.ManageCourseBO;
import Business.Custom.ManageRegisterStudentBO;
import Dto.StudentRegisterDto;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.Util.StudentRegisteredData;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManageStudentController {
    public AnchorPane ManageStudentRoot;
    public JFXTextField txtStdID;
    public TableView<StudentRegisteredData> tblStudentManagement;

    private ManageRegisterStudentBO studentRegBO = BOFactory.getInstance().getBO(BOFactory.BOType.StudentReg_BO);
    ObservableList<StudentRegisteredData> list = null;

    public void initialize() throws SQLException {
        tblStudentManagement.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("studentID"));
        tblStudentManagement.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("studentName"));
        tblStudentManagement.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("batchID"));
        tblStudentManagement.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("registeredDate"));

        List<StudentRegisterDto> regStudent = studentRegBO.getRegStudent();
        List<StudentRegisteredData> rgstd = new ArrayList<>();
        for (StudentRegisterDto s : regStudent) {
            rgstd.add(new StudentRegisteredData(s.getStudentID(), s.getStudentName(), s.getBatchID(), s.getRegisteredDate()));

        }
        list = FXCollections.observableArrayList(rgstd);
        tblStudentManagement.setItems(list);
    }


    public void HomePage(ActionEvent actionEvent) throws IOException {

        Parent customerRoot = FXMLLoader.load(this.getClass().getResource("/view/DashForm.fxml"));
        Scene customerScene = new Scene(customerRoot);
        Stage primaryStage = (Stage) ManageStudentRoot.getScene().getWindow();
        primaryStage.setScene(customerScene);
    }

    public void searchStudentDetails(KeyEvent keyEvent) {
        ObservableList<StudentRegisteredData> temObList=FXCollections.observableArrayList();
        for (StudentRegisteredData l:list) {
            if(l.getStudentID().startsWith(txtStdID.getText())){
                temObList.add(l);

            }
        }
        tblStudentManagement.setItems(temObList);

    }
}
