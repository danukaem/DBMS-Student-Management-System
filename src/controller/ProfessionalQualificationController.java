package controller;

import DB.DBConnection;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.Util.QualificationData;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ProfessionalQualificationController {
    public AnchorPane ProfessionalQualificationRoot;
    public JFXTextField txtQualification;
    public JFXTextField txtInstitute;
    public JFXTextField txtSpecilazation;
    public JFXDatePicker txtAwardDate;
    public TableView<QualificationData> tblQualification;
    public Button btnRemove;
    public Button btnUpdate;
    ArrayList<QualificationData> qalificationarray= new ArrayList<>();
    ObservableList<QualificationData>  qualificationList=null;

    public void Home(ActionEvent actionEvent) throws IOException {

        Parent customerRoot = FXMLLoader.load(this.getClass().getResource("/view/RegisterStudent.fxml"));
        Scene customerScene = new Scene(customerRoot);
        Stage primaryStage = (Stage) ProfessionalQualificationRoot.getScene().getWindow();
        primaryStage.setScene(customerScene);
    }


    public void selectRow(MouseEvent mouseEvent) {
        QualificationData tbl = tblQualification.getSelectionModel().getSelectedItem();
        if(tbl!= null){
            btnRemove.setDisable(false);
            btnUpdate.setDisable(false);
        }
        txtQualification.setText(tbl.getQualification());
        txtInstitute.setText(tbl.getInstitute());
        txtAwardDate.setValue(tbl.getAwardDate());
        txtSpecilazation.setText(tbl.getSpecialization());


    }

    public void addQualification(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DBConnection.getConnection();

        String qlf = txtQualification.getText().trim();
        PreparedStatement selectId = connection.prepareStatement("select * from qualifications where qualification=?");
        selectId.setObject(1, qlf);
        ResultSet resultSet = selectId.executeQuery();
        if (resultSet.next()) {

            new Alert(Alert.AlertType.WARNING, "This Qualification Already added", ButtonType.OK).show();
            ClearTextFeilds();
        } else {
            PreparedStatement scfc = connection.prepareStatement("insert into qualifications values (?,?,?,?,?)");
            scfc.setObject(1, RegisterStudentController.stdID);
            scfc.setObject(2, txtQualification.getText().trim());
            scfc.setObject(3, txtInstitute.getText().trim());
            scfc.setObject(4, txtAwardDate.getValue());
            scfc.setObject(5, txtSpecilazation.getText().trim());
            int rslt = scfc.executeUpdate();
            if (rslt > 0) {
                new Alert(Alert.AlertType.CONFIRMATION, "Successful ", ButtonType.OK).show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Unsuccessful", ButtonType.OK).show();
            }
            TableUpdate();
            ClearTextFeilds();
        }


    }

    public void removeQualification(ActionEvent actionEvent) {
    }

    public void updateQualification(ActionEvent actionEvent) {
    }

    public void newQualification(ActionEvent actionEvent) {
    }
    void ClearTextFeilds() {
        txtSpecilazation.setText(null);
        txtAwardDate.setValue(null);
        txtInstitute.setText(null);
        txtQualification.setText(null);

    }
    void TableUpdate() throws ClassNotFoundException, SQLException {
        TableClear();
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DBConnection.getConnection();
        PreparedStatement scfc = connection.prepareStatement("select * from qualifications");
        ResultSet rst = scfc.executeQuery();
        while (rst.next()) {

                LocalDate localDate = rst.getDate(4).toLocalDate();
            qalificationarray.add(new QualificationData(rst.getString(1),rst.getString(2),rst.getString(3)
                    ,localDate,rst.getString(5)));
            tblQualification.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("qualification"));
            tblQualification.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("institute"));
            tblQualification.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("awardDate"));
            tblQualification.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("specialization"));
            qualificationList = FXCollections.observableList(qalificationarray);
            tblQualification.setItems(qualificationList);
        }
    }

    void TableClear() {
        if (qualificationList != null) {
            qualificationList.removeAll(qualificationList);
        }
    }




    }
