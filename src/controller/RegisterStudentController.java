package controller;

import DB.DBConnection;
import com.jfoenix.controls.JFXComboBox;
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
import view.Util.StudentDetails;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class RegisterStudentController {
    public AnchorPane registerRoot;
    public JFXTextField txtStdInitialName;
    public JFXTextField txtFullName;
    public JFXTextField txtAddress;
    public JFXTextField txtTelephone;
    public JFXTextField txtEmail;
    public JFXTextField txtMobile;
    public JFXTextField txtNIC;
    public JFXTextField txtStdtId;
    public JFXTextField txtCity;
    public JFXDatePicker txtDate;
    public JFXTextField txtSchool;
    public JFXTextField txtUniversity;
    public JFXTextField txtFaculty;
    public JFXComboBox cmbCourse;
    public JFXComboBox cmbBatch;
    public JFXComboBox cmbGender;
    public TableView<StudentDetails> tblRegistration;
    public JFXComboBox cmbQualification;
    public Button btnRegister;
    public Button btnAdd;
    public Button btnRemove;
    public Button btnUpdate;
    public Button btnHome;
    public Button btnProfQlification;
    public Button btnGurdian;
    ArrayList<StudentDetails> studentArray = new ArrayList<>();
    ObservableList<StudentDetails> studentDetailsObservableList = null;
    public static String stdID;
    public static String batchID;


    public void homePage(ActionEvent actionEvent) throws IOException {

        btnAdd.setDisable(false);
        Parent customerRoot = FXMLLoader.load(this.getClass().getResource("/view/DashForm.fxml"));
        Scene customerScene = new Scene(customerRoot);
        Stage primaryStage = (Stage) registerRoot.getScene().getWindow();
        primaryStage.setScene(customerScene);
    }

    public void ProfessionalFormNavigate(ActionEvent actionEvent) throws IOException {
        Parent customerRoot = FXMLLoader.load(this.getClass().getResource("/view/ProfessionalQualificationForm.fxml"));
        Scene customerScene = new Scene(customerRoot);
        Stage primaryStage = (Stage) registerRoot.getScene().getWindow();
        primaryStage.setScene(customerScene);
    }

    public void GurdianFormNavigate(ActionEvent actionEvent) throws IOException {


        btnRegister.setDisable(false);

        Parent customerRoot = FXMLLoader.load(this.getClass().getResource("/view/ParentDetailsForm.fxml"));
        Scene customerScene = new Scene(customerRoot);
        Stage primaryStage = (Stage) registerRoot.getScene().getWindow();
        primaryStage.setScene(customerScene);
    }

    public void initialize() throws SQLException, ClassNotFoundException {
        btnAdd.setDisable(false);
        btnUpdate.setDisable(true);
        btnRemove.setDisable(true);

        generateComBox();
        cmbGender.getItems().add("Male");
        cmbGender.getItems().add("Female");
        cmbQualification.getItems().add("Master");
        cmbQualification.getItems().add("Degree");
        cmbQualification.getItems().add("Diploma");
        cmbQualification.getItems().add("Advanced Level");
        cmbQualification.getItems().add("Ordinary Level");
        cmbQualification.getItems().add("Other");
    }

    void generateComBox() throws ClassNotFoundException, SQLException {
        cmbBatch.getItems().clear();
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DBConnection.getConnection();
        PreparedStatement scfc1 = connection.prepareStatement("select coursename from courses");
        ResultSet resultSet1 = scfc1.executeQuery();
        while (resultSet1.next()) {
            cmbCourse.getItems().add(resultSet1.getString(1));
        }
    }


    public void selectBatch(MouseEvent mouseEvent) throws ClassNotFoundException, SQLException {
        cmbBatch.getItems().clear();
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DBConnection.getConnection();
        PreparedStatement scfc2 = connection.prepareStatement("select * from batches where coursename=?");
        scfc2.setObject(1, cmbCourse.getValue());
        ResultSet resultSet2 = scfc2.executeQuery();
        while (resultSet2.next()) {
            cmbBatch.getItems().add(resultSet2.getString(3));
        }
    }

    public void RemoveDetails(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {

        if (tblRegistration.getSelectionModel().isEmpty()) {
            btnRegister.setDisable(true);
            return;
        }
        StudentDetails selectedItem = tblRegistration.getSelectionModel().getSelectedItem();
        String sFullnName = selectedItem.getFullName();
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DBConnection.getConnection();
        PreparedStatement scfc = connection.prepareStatement("delete from studentdetails where fullname=?");
        scfc.setObject(1, sFullnName);
        int i = scfc.executeUpdate();
        if (i > 0) {
            new Alert(Alert.AlertType.CONFIRMATION, "Successful ", ButtonType.OK).show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Unsuccessful", ButtonType.OK).show();
        }

        TableUpdate();
        tblRegistration.getSelectionModel().clearSelection();

    }

    public void btnNewRegistration(ActionEvent actionEvent) {
        ClearTextFeilds();
        cmbCourse.requestFocus();

    }

    public void registerDetails(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {

        if(tblRegistration.getItems().isEmpty()){
            new Alert(Alert.AlertType.WARNING, "Please fill data", ButtonType.OK).show();

            return;
        }
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DBConnection.getConnection();

        PreparedStatement scfc = connection.prepareStatement("insert into registration values (?,?)");
        scfc.setObject(1, stdID);
        scfc.setObject(2, batchID);
        System.out.println("success  stdID " + stdID + "  batch id " + batchID);
        stdID = null;
        batchID = null;
        int rslt = scfc.executeUpdate();
        if (rslt > 0) {
            new Alert(Alert.AlertType.CONFIRMATION, "Successful ", ButtonType.OK).show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Unsuccessful", ButtonType.OK).show();
        }

        TableClear();

    }

    void alert(){
        new Alert(Alert.AlertType.WARNING,"please complete form",ButtonType.OK).show();

    }
    public void addDetails(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        if(txtStdtId.getText().trim()==null){
            alert();

        }
        else if(txtStdInitialName.getText().trim()==null){
            alert();return;
        }  else if(txtFullName.getText().trim()==null){
            alert();return;
        }  else if(txtAddress.getText().trim()==null){
            alert();return;
        }  else if(txtTelephone.getText().trim()==null){
            alert();return;
        }  else if(txtEmail.getText().trim()==null){
            alert();return;
        }  else if(txtCity.getText().trim()==null){
            alert();return;
        }  else if(txtNIC.getText().trim()==null){
            alert();return;
        }  else if(txtMobile.getText().trim()==null){
            alert();return;
        }  else if(txtSchool.getText().trim()==null){
            alert();return;
        }  else if(txtUniversity.getText().trim()==null){
            alert();return;
        }  else if(txtFaculty.getText().trim()==null){
            alert();return;
        }else if(txtDate.getValue()==null){
            alert();return;
        }else if(cmbGender.getSelectionModel().isEmpty() ||cmbQualification.getSelectionModel().isEmpty()||cmbBatch.getSelectionModel().isEmpty()||cmbCourse.getSelectionModel().isEmpty()){
            alert();return;
        }

        btnAdd.setDisable(false);
        btnUpdate.setDisable(true);
        btnRemove.setDisable(true);
        btnProfQlification.setDisable(false);
        btnGurdian.setDisable(false);
        btnRegister.setDisable(true);

        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DBConnection.getConnection();

        String stdId = txtStdtId.getText().trim();
        stdID = txtStdtId.getText().trim();
        PreparedStatement selectId = connection.prepareStatement("select * from studentdetails where studentid=?");
        selectId.setObject(1, stdId);
        ResultSet resultSet = selectId.executeQuery();
        if (resultSet.next()) {
            new Alert(Alert.AlertType.WARNING, "This Batch id Already Exist", ButtonType.OK).show();
            ClearTextFeilds();
        } else {
            batchID = cmbBatch.getValue().toString();

            PreparedStatement scfc = connection.prepareStatement("insert into studentdetails values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            scfc.setObject(1, txtStdtId.getText().trim());
            scfc.setObject(2, txtStdInitialName.getText().trim());
            scfc.setObject(3, txtFullName.getText().trim());
            scfc.setObject(4, txtAddress.getText().trim());
            scfc.setObject(5, txtCity.getText().trim());
            scfc.setObject(6, Integer.parseInt(txtTelephone.getText().trim()));
            scfc.setObject(7, Integer.parseInt(txtMobile.getText().trim()));
            scfc.setObject(8, txtEmail.getText().trim());
            scfc.setObject(9, txtDate.getValue());
            scfc.setObject(10, cmbGender.getValue());
            scfc.setObject(11, txtNIC.getText().trim());
            scfc.setObject(12, txtSchool.getText().trim());
            scfc.setObject(13, txtUniversity.getText().trim());
            scfc.setObject(14, txtFaculty.getText().trim());
            scfc.setObject(15, cmbCourse.getValue());
            scfc.setObject(16, cmbBatch.getValue());
            scfc.setObject(17, cmbQualification.getValue());
            int rslt = scfc.executeUpdate();
            if (rslt > 0) {
                new Alert(Alert.AlertType.CONFIRMATION, "Successful ", ButtonType.OK).show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Unsuccessful", ButtonType.OK).show();
            }
            TableUpdate();
            ClearTextFeilds();
            cmbCourse.getSelectionModel().clearSelection();

        }
    }

    void ClearTextFeilds() {
        txtStdInitialName.setText(null);
        txtFullName.setText(null);
        txtAddress.setText(null);
        txtTelephone.setText(null);
        txtEmail.setText(null);
        txtMobile.setText(null);
        txtNIC.setText(null);
        txtStdtId.setText(null);
        txtCity.setText(null);
        txtDate.getEditor().clear();
        txtSchool.setText(null);
        txtUniversity.setText(null);
        txtFaculty.setText(null);
        cmbCourse.getSelectionModel().clearSelection();
        cmbBatch.getSelectionModel().clearSelection();
        cmbQualification.getSelectionModel().clearSelection();
        cmbGender.getSelectionModel().clearSelection();
    }

    void TableUpdate() throws ClassNotFoundException, SQLException {
        TableClear();
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DBConnection.getConnection();
        PreparedStatement scfc = connection.prepareStatement("select * from studentdetails");
        ResultSet rst = scfc.executeQuery();
        while (rst.next()) {
            LocalDate localDate = rst.getDate(9).toLocalDate();
            studentArray.add(new StudentDetails(rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(6), rst.getString(8),
                    rst.getString(7), rst.getString(11), rst.getString(1), rst.getString(5), localDate, rst.getString(12),
                    rst.getString(13), rst.getString(14), rst.getString(15), rst.getString(16), rst.getString(10), rst.getString(17)));
            tblRegistration.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("StdtId"));
            tblRegistration.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("FullName"));
            tblRegistration.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("cmbCourse"));
            tblRegistration.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("cmbBatch"));
            tblRegistration.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("Date"));
            studentDetailsObservableList = FXCollections.observableList(studentArray);
            tblRegistration.setItems(studentDetailsObservableList);
        }
    }

    void TableClear() {
        if (studentDetailsObservableList != null) {
            studentDetailsObservableList.removeAll(studentDetailsObservableList);
        }
    }

    public void clearComboBox(ActionEvent actionEvent) {
        cmbBatch.getItems().clear();
    }


    public void updateDetails(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {

        if (tblRegistration.getSelectionModel().isEmpty()) {
            btnRegister.setDisable(true);
            return;
        }

        StudentDetails selectedItem = tblRegistration.getSelectionModel().getSelectedItem();
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DBConnection.getConnection();
        PreparedStatement scfc = connection.prepareStatement("UPDATE studentdetails set studentid=?,namewithinitials=?,fullname=?,address=?," +
                "city=?,telephonenumber=?,mobile1=?,email=?," +
                "dateofbirth=?,gender=?,nic=?,school=?," +
                "university=?,faculty=?,coursename=?,batchid=?," +
                "higherQualification=? where studentid=? ");
        scfc.setObject(1, txtStdtId.getText().trim());
        scfc.setObject(2, txtStdInitialName.getText().trim());
        scfc.setObject(3, txtFullName.getText().trim());
        scfc.setObject(4, txtAddress.getText().trim());
        scfc.setObject(5, txtCity.getText().trim());
        scfc.setObject(6, Integer.parseInt(txtTelephone.getText().trim()));
        scfc.setObject(7, Integer.parseInt(txtMobile.getText().trim()));
        scfc.setObject(8, txtEmail.getText().trim());
        scfc.setObject(9, txtDate.getValue());
        scfc.setObject(10, cmbGender.getValue());
        scfc.setObject(11, txtNIC.getText().trim());
        scfc.setObject(12, txtSchool.getText().trim());
        scfc.setObject(13, txtUniversity.getText().trim());
        scfc.setObject(14, txtFaculty.getText().trim());
        scfc.setObject(15, cmbCourse.getValue());
        scfc.setObject(16, cmbBatch.getValue());
        scfc.setObject(17, cmbQualification.getValue());
        scfc.setObject(18, txtStdtId.getText().trim());
        int i = scfc.executeUpdate();
        if (i > 0) {
            new Alert(Alert.AlertType.CONFIRMATION, "Successful ", ButtonType.OK).show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Unsuccessful", ButtonType.OK).show();
        }
        TableUpdate();
        ClearTextFeilds();
        txtStdtId.setEditable(true);
        tblRegistration.getSelectionModel().clearSelection();

    }

    public void selectRow(MouseEvent mouseEvent) {

        StudentDetails tbl = tblRegistration.getSelectionModel().getSelectedItem();
        if (tbl != null) {
            btnRemove.setDisable(false);
            btnUpdate.setDisable(false);
        }

        txtStdtId.setEditable(false);
        txtStdInitialName.setText(tbl.getStdInitialName());
        txtFullName.setText(tbl.getFullName());
        txtAddress.setText(tbl.getAddress());
        txtTelephone.setText(tbl.getTelephone());
        txtEmail.setText(tbl.getEmail());
        txtMobile.setText(tbl.getMobile());
        txtNIC.setText(tbl.getNIC());
        txtStdtId.setText(tbl.getStdtId());
        txtCity.setText(tbl.getCity());
        txtSchool.setText(tbl.getSchool());
        txtUniversity.setText(tbl.getUniversity());
        txtFaculty.setText(tbl.getFaculty());
        cmbCourse.setValue(tbl.getCmbCourse());
        cmbBatch.setValue(tbl.getCmbBatch());
        System.out.println(tbl.getCmbBatch());
        cmbQualification.setValue(tbl.getCmbQualification());
        cmbGender.setValue(tbl.getCmbGender());
        txtDate.setValue(tbl.getDate());


    }
}
