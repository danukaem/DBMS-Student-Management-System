package controller;

import Business.BOFactory;
import Business.Converter;
import Business.Custom.ManageBatchBO;
import DB.DBConnection;
import Dto.BatchDto;
import Entity.Batch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.Util.BatchData;
import view.Util.CourseData;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ManageBatchController {
    public AnchorPane batchRoot;
    public ComboBox<String> cmbCourse;
    public TextField txtBatchId;
    public TextField txtbatchDescription;
    public TextField txtBatchCapacity;
    public TableView<BatchData> tblBatchdata;
    public Button btnsaveBatch;
    public Button btnupdatebatch;
    public Button btndeleteBatch;
    public DatePicker txtBatchStartDate;
    ArrayList<BatchData> batchdata = new ArrayList<>();
    ObservableList<BatchData> list = null;
    public String coursename = null;

    ManageBatchBO manageBatchBO = BOFactory.getInstance().getBO(BOFactory.BOType.Batch_BO);


    public void initialize() throws SQLException, ClassNotFoundException {
        TableUpdate();
        generateComBox();
        cmbCourse.setPromptText("select course ");


    }

    public void generateComBox() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DBConnection.getConnection();
        PreparedStatement scfc = connection.prepareStatement("select coursename from courses");
        ResultSet resultSet = scfc.executeQuery();
        while (resultSet.next()) {
            cmbCourse.getItems().add(resultSet.getString(1));
        }

    }

    public void TableClear() {
        if (list != null) {
            list.removeAll(list);
        }
    }

    public void TableUpdate() throws ClassNotFoundException, SQLException {
        TableClear();
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DBConnection.getConnection();
        PreparedStatement scfc = connection.prepareStatement("select * from batches");
        ResultSet resultSet = scfc.executeQuery();
        while (resultSet.next()) {
            LocalDate localDate = resultSet.getDate(4).toLocalDate();
            batchdata.add(new BatchData(resultSet.getString(2), resultSet.getString(3), localDate, resultSet.getString(5), resultSet.getInt(6), resultSet.getString(1)));
        }

        tblBatchdata.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("course"));
        tblBatchdata.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("batchId"));
        tblBatchdata.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("date"));
        tblBatchdata.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblBatchdata.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("batchCapacity"));
        list = FXCollections.observableList(batchdata);
        tblBatchdata.setItems(list);


    }


    public void ClearTextFeilds() {
        txtBatchId.setText(null);
        //txtBatchStartDate.getEditor().clear();
        txtBatchStartDate.setPromptText("");
        txtbatchDescription.setText(null);
        txtBatchCapacity.setText(null);
        cmbCourse.getSelectionModel().clearSelection();
    }

    public void homePage(ActionEvent actionEvent) throws IOException {
        Parent customerRoot = FXMLLoader.load(this.getClass().getResource("/view/DashForm.fxml"));
        Scene customerScene = new Scene(customerRoot);
        Stage primaryStage = (Stage) batchRoot.getScene().getWindow();
        primaryStage.setScene(customerScene);
    }


    public void saveBatch(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        String courseId = manageBatchBO.getCourseID(cmbCourse.getValue());
        BatchDto batch = new BatchDto(cmbCourse.getValue(), txtBatchId.getText(), txtBatchStartDate.getValue(), txtbatchDescription.getText(), Integer.parseInt(txtBatchCapacity.getText()), courseId);
        BatchData batchData = new BatchData(cmbCourse.getValue(), txtBatchId.getText(), txtBatchStartDate.getValue(), txtbatchDescription.getText(), Integer.parseInt(txtBatchCapacity.getText()), courseId);
        tblBatchdata.getItems().add(batchData);
        boolean result = manageBatchBO.createBatch(batch);
        if (result) {
            new Alert(Alert.AlertType.INFORMATION, "course has been saved successfully", ButtonType.OK).showAndWait();
            tblBatchdata.scrollTo(batchData);
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to save the course", ButtonType.OK).showAndWait();
        }
        tblBatchdata.getSelectionModel().clearSelection();
    }

    public void updateBatch(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        BatchData selectedItem = tblBatchdata.getSelectionModel().getSelectedItem();
        String courseId = manageBatchBO.getCourseID(cmbCourse.getValue());
        BatchDto batch = new BatchDto(cmbCourse.getValue(), txtBatchId.getText(), txtBatchStartDate.getValue(), txtbatchDescription.getText(), Integer.parseInt(txtBatchCapacity.getText()), courseId);
        boolean result = manageBatchBO.updateBatch(batch);
        BatchData batchData = new BatchData(cmbCourse.getValue(), txtBatchId.getText(), txtBatchStartDate.getValue(), txtbatchDescription.getText(), Integer.parseInt(txtBatchCapacity.getText()), courseId);
        tblBatchdata.getItems().add(batchData);
        if (result) {
            new Alert(Alert.AlertType.INFORMATION, "course has been updated successfully", ButtonType.OK).showAndWait();
            tblBatchdata.scrollTo(batchData);
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to update the course", ButtonType.OK).showAndWait();
        }
        TableUpdate();
        ClearTextFeilds();
        tblBatchdata.getSelectionModel().clearSelection();
    }

    public void deleteBatch(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {

        BatchData selectedItem = tblBatchdata.getSelectionModel().getSelectedItem();
        String batchId = selectedItem.getBatchId();
        String courseId = manageBatchBO.getCourseID(cmbCourse.getValue());
        boolean result = manageBatchBO.deleteBatch(batchId);
        BatchData batchData = new BatchData(cmbCourse.getValue(), txtBatchId.getText(), txtBatchStartDate.getValue(), txtbatchDescription.getText(), Integer.parseInt(txtBatchCapacity.getText()), courseId);
        tblBatchdata.getItems().add(batchData);
        if (result) {
            new Alert(Alert.AlertType.INFORMATION, "course has been deleted successfully", ButtonType.OK).showAndWait();
            tblBatchdata.scrollTo(batchData);
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to delete the course", ButtonType.OK).showAndWait();
        }
        TableUpdate();
        tblBatchdata.getSelectionModel().clearSelection();
    }

    public void selectCourse(MouseEvent mouseEvent) {
        coursename = cmbCourse.getSelectionModel().getSelectedItem();
    }

    public void selectBatch(MouseEvent mouseEvent) {

        BatchData selectedItem = tblBatchdata.getSelectionModel().getSelectedItem();
        txtBatchId.setText(selectedItem.getBatchId());
        txtBatchStartDate.setValue(selectedItem.getDate());
        cmbCourse.setValue(selectedItem.getCourse());
        txtbatchDescription.setText(selectedItem.getDescription());
        txtBatchCapacity.setText(Integer.toString(selectedItem.getBatchCapacity()));
    }

    public void newBatch(ActionEvent actionEvent) {
        ClearTextFeilds();
        cmbCourse.requestFocus();
        tblBatchdata.getSelectionModel().clearSelection();
    }
}
