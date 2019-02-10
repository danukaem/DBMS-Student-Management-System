package controller;

import Business.BOFactory;
import Business.Converter;
import Business.Custom.Impl.ManageCourseBOImpl;
import Business.Custom.ManageCourseBO;
import DB.DBConnection;
import Dto.CourseDto;
import Entity.Course;
import Entity.SuperEntity;
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
import view.Util.CourseData;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ManageCourseController {
    public AnchorPane courseRoot;
    public TextField txtCourseName;
    public TextField txtCourseDiscription;
    public TextField txtCourseDuration;
    public TableView<CourseData> tblCourseDetails;
    public TextField txtCourseID;
    ArrayList<CourseData> coursedata = new ArrayList();
    ObservableList<CourseData> list = null;

    private ManageCourseBO manageCourseBO = BOFactory.getInstance().getBO(BOFactory.BOType.Course_BO);

    public void initialize() throws SQLException, ClassNotFoundException {
        TableUpdate();
    }

    void TableClear() {
        if (list != null) {
            list.removeAll(list);
        }
    }

    void TableUpdate() throws ClassNotFoundException, SQLException {
        TableClear();
        List<SuperEntity> courses = Converter.getEntityList(manageCourseBO.getCourse());

        for (SuperEntity course : courses) {
            Course c= (Course) course;
            coursedata.add(new CourseData(c.getCourseId(),c.getCourseName(),c.getCourseDescription(),c.getDuration()));
        }

        tblCourseDetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("courseId"));
        tblCourseDetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("courseName"));
        tblCourseDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("courseDescription"));
        tblCourseDetails.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("duration"));
        list = FXCollections.observableList(coursedata);
        tblCourseDetails.setItems(list);
    }

    public void homePage(ActionEvent actionEvent) throws IOException {
        Parent customerRoot = FXMLLoader.load(this.getClass().getResource("/view/DashForm.fxml"));
        Scene customerScene = new Scene(customerRoot);
        Stage primaryStage = (Stage) courseRoot.getScene().getWindow();
        primaryStage.setScene(customerScene);
        TableClear();
    }

    public void saveCourse(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {

        if (txtCourseID.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "course ID is empty", ButtonType.OK).showAndWait();
            txtCourseID.requestFocus();
            return;
        } else if (txtCourseName.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "course Name is empty", ButtonType.OK).showAndWait();
            txtCourseName.requestFocus();
            return;
        } else if (txtCourseDiscription.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "course Description is empty", ButtonType.OK).showAndWait();
            txtCourseDiscription.requestFocus();
            return;
        } else if (txtCourseDuration.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "course Duration is empty", ButtonType.OK).showAndWait();
            txtCourseDuration.requestFocus();
            return;
        }
        if (tblCourseDetails.getSelectionModel().isEmpty()) {
            ObservableList<CourseData> items = tblCourseDetails.getItems();
            for (CourseData customer : items) {
                if (customer.getCourseId().equals(txtCourseID.getText())) {
                    new Alert(Alert.AlertType.ERROR, "Duplicate course IDs are not allowed").showAndWait();
                    txtCourseID.requestFocus();
                    return;
                }
            }

            CourseData courseData = new CourseData(txtCourseID.getText(), txtCourseName.getText(), txtCourseDiscription.getText(), Integer.parseInt(txtCourseDuration.getText()));
            tblCourseDetails.getItems().add(courseData);
            CourseDto courseDto = new CourseDto(txtCourseID.getText(), txtCourseName.getText(), txtCourseDiscription.getText(), Integer.parseInt(txtCourseDuration.getText()));
            boolean result = manageCourseBO.createCourse(courseDto);

            if (result) {
                new Alert(Alert.AlertType.INFORMATION, "course has been saved successfully", ButtonType.OK).showAndWait();
                tblCourseDetails.scrollTo(courseData);
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save the course", ButtonType.OK).showAndWait();
            }
            ClearTextFeilds();
        } else {

            CourseData selectedCourse = tblCourseDetails.getSelectionModel().getSelectedItem();
            selectedCourse.setCourseName(txtCourseName.getText());
            selectedCourse.setCourseDescription(txtCourseDiscription.getText());
            selectedCourse.setDuration(Integer.parseInt(txtCourseDuration.getText()));

            tblCourseDetails.refresh();

            String selectedCustomerID = tblCourseDetails.getSelectionModel().getSelectedItem().getCourseId();

            boolean result = manageCourseBO.updateCourse(new CourseDto(txtCourseID.getText(),
                    txtCourseName.getText(),
                    txtCourseDiscription.getText(), Integer.parseInt(txtCourseDuration.getText())));

            if (result) {
                new Alert(Alert.AlertType.INFORMATION, "course has been updated successfully", ButtonType.OK).showAndWait();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update the course", ButtonType.OK).showAndWait();
            }
            ClearTextFeilds();
        }
        tblCourseDetails.getSelectionModel().clearSelection();

    }

    public void deleteCourse(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {


        Alert confirmMsg = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to delete this course?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = confirmMsg.showAndWait();

        if (buttonType.get() == ButtonType.YES) {
            String selectedCourse = tblCourseDetails.getSelectionModel().getSelectedItem().getCourseId();

            tblCourseDetails.getItems().remove(tblCourseDetails.getSelectionModel().getSelectedItem());
            boolean result = manageCourseBO.deleteCourse(selectedCourse);
            if (!result) {
                new Alert(Alert.AlertType.ERROR, "Failed to delete the course", ButtonType.OK).showAndWait();
            } else {
                ClearTextFeilds();
            }
        }
        tblCourseDetails.getSelectionModel().clearSelection();
    }

    public void updateCourse(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
//        CourseData selectedItem = tblCourseDetails.getSelectionModel().getSelectedItem();
//
//
//        Class.forName("com.mysql.jdbc.Driver");
//        Connection connection = DBConnection.getConnection();
//        PreparedStatement scfc = connection.prepareStatement("UPDATE courses set coursename=?,coursedescription=?,courseduration=?,courseid=? where courseid=? ");
//        scfc.setObject(1, txtCourseName.getText().trim());
//        scfc.setObject(2, txtCourseDiscription.getText().trim());
//        scfc.setObject(3, txtCourseDuration.getText().trim());
//        scfc.setObject(4, txtCourseID.getText().trim());
//        scfc.setObject(5, selectedItem.getCourseId());
//        int i = scfc.executeUpdate();
//        if (i > 0) {
//            new Alert(Alert.AlertType.CONFIRMATION, "Successful ", ButtonType.OK).show();
//        } else {
//            new Alert(Alert.AlertType.WARNING, "Unsuccessful", ButtonType.OK).show();
//        }
//
//        TableUpdate();
//        ClearTextFeilds();

    }


    public void selectingTableRaw(MouseEvent mouseEvent) {
        CourseData selectedItem = tblCourseDetails.getSelectionModel().getSelectedItem();
        txtCourseID.setText(selectedItem.getCourseId());
        txtCourseName.setText(selectedItem.getCourseName());
        txtCourseDiscription.setText(selectedItem.getCourseDescription());
        txtCourseDuration.setText(Integer.toString(selectedItem.getDuration()));

    }

    public void newCourse(ActionEvent actionEvent) {
        ClearTextFeilds();
        tblCourseDetails.getSelectionModel().clearSelection();

    }

    void ClearTextFeilds() {
        txtCourseID.setText(null);
        txtCourseName.setText(null);
        txtCourseDiscription.setText(null);
        txtCourseDuration.setText(null);
        txtCourseID.requestFocus();
    }

    private void reset() {


    }

}
