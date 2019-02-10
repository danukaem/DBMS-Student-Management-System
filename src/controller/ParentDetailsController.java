package controller;

import Business.BOFactory;
import Business.Converter;
import Business.Custom.ManageCourseBO;
import Business.Custom.ManageParentBO;
import DB.DBConnection;
import Dto.CourseDto;
import Dto.ParentDto;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.Util.CourseData;
import view.Util.GurdianData;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParentDetailsController {
    public AnchorPane ParentDetailsRoot;
    public JFXTextField txtParentName;
    public JFXTextField txtTelephone;
    public JFXTextField txtMobile1;
    public JFXTextField txtEmail;
    public JFXTextField txtDesignation;
    public JFXTextField txtWorkingPlace;
    public JFXTextField txtAddress;
    public JFXTextField txtMoble2;
    public TableView<GurdianData> tblGurdianDetails;

    ArrayList<GurdianData> arraylist = new ArrayList<GurdianData>();
    ObservableList<GurdianData> observableList = null;

    private ManageParentBO manageParentBO = BOFactory.getInstance().getBO(BOFactory.BOType.Parent_BO);


    public void initialize() throws SQLException, ClassNotFoundException {
       // TableUpdate();
    }

    void TableClear() {
        if (observableList != null) {
            observableList.removeAll(observableList);
        }
    }

    void TableUpdate() throws ClassNotFoundException, SQLException {
        TableClear();
        Class.forName("com.mysql.jdbc.Driver");
        List<ParentDto> parents = manageParentBO.getParents();
        for (ParentDto parentDto:parents) {
            arraylist.add(new GurdianData( parentDto.getName(), parentDto.getTelephone(),parentDto.getMobile()
                    , parentDto.getEmail(), parentDto.getDesignation(), parentDto.getWorkPlace(),
                    parentDto.getAddress()));
        }

        tblGurdianDetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblGurdianDetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("telephone"));
        tblGurdianDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("mobile"));
        tblGurdianDetails.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("email"));
        tblGurdianDetails.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("designation"));
        tblGurdianDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("workPlace"));
        tblGurdianDetails.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("address"));
        observableList = FXCollections.observableList(arraylist);
        tblGurdianDetails.setItems(observableList);
    }

    void ClearTextFeilds() {
        txtParentName.setText(null);
        txtTelephone.setText(null);
        txtMobile1.setText(null);
        txtMoble2.setText(null);
        txtEmail.setText(null);
        txtDesignation.setText(null);
        txtWorkingPlace.setText(null);
        txtAddress.setText(null);
    }


    public void back(ActionEvent actionEvent) throws IOException {

        Parent customerRoot = FXMLLoader.load(this.getClass().getResource("/view/RegisterStudent.fxml"));
        Scene customerScene = new Scene(customerRoot);
        Stage primaryStage = (Stage) ParentDetailsRoot.getScene().getWindow();
        primaryStage.setScene(customerScene);
    }

    public void addGurdian(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {

        if (tblGurdianDetails.getSelectionModel().isEmpty()) {
            ObservableList<GurdianData> parents = tblGurdianDetails.getItems();
            for (GurdianData parent : parents) {
                if (parent.getName().equals(txtParentName.getText())) {
                    new Alert(Alert.AlertType.ERROR, "Duplicate parent are not allowed").showAndWait();
                    txtParentName.requestFocus();
                    return;
                }
            }
            GurdianData parentdata = new GurdianData(txtParentName.getText(), txtTelephone.getText(), txtMobile1.getText(),
                    txtEmail.getText(), txtDesignation.getText(), txtWorkingPlace.getText(), txtAddress.getText());
            tblGurdianDetails.getItems().add(parentdata);

            ParentDto parentDto = new ParentDto(RegisterStudentController.stdID, txtParentName.getText(), txtTelephone.getText(),
                    txtMobile1.getText(), txtMoble2.getText(), txtEmail.getText(), txtDesignation.getText(),
                    txtWorkingPlace.getText(), txtAddress.getText());
            boolean result = manageParentBO.createParents(parentDto);

            if (result) {
                new Alert(Alert.AlertType.INFORMATION, "Parent has been saved successfully", ButtonType.OK).showAndWait();
                tblGurdianDetails.scrollTo(parentdata);
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save the Parent", ButtonType.OK).showAndWait();
            }

            TableUpdate();
            ClearTextFeilds();
        }
    }

    public void removeGurdian(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        GurdianData selectedItem = tblGurdianDetails.getSelectionModel().getSelectedItem();
        String parentName = selectedItem.getName();
        Class.forName("com.mysql.jdbc.Driver");
        boolean result = manageParentBO.deleteParents(parentName);
        if (result ) {
            new Alert(Alert.AlertType.CONFIRMATION, "Successful ", ButtonType.OK).show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Unsuccessful", ButtonType.OK).show();
        }

        TableUpdate();
    }

    public void UpdateGurdian(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        GurdianData selectedParent = tblGurdianDetails.getSelectionModel().getSelectedItem();

        selectedParent.setAddress(txtAddress.getText());
        selectedParent.setDesignation(txtDesignation.getText());
        selectedParent.setEmail(txtEmail.getText());
        selectedParent.setMobile(txtMobile1.getText());
        selectedParent.setName(txtParentName.getText());
        selectedParent.setTelephone(txtTelephone.getText());
        selectedParent.setWorkPlace(txtWorkingPlace.getText());

        ParentDto parentDto = new ParentDto(RegisterStudentController.stdID, txtParentName.getText(), txtTelephone.getText(),
                txtMobile1.getText(), txtMoble2.getText(), txtEmail.getText(), txtDesignation.getText(),
                txtWorkingPlace.getText(), txtAddress.getText());
        boolean result = manageParentBO.updateParents(parentDto);

        if (result) {
            new Alert(Alert.AlertType.INFORMATION, "Parent has been saved successfully", ButtonType.OK).showAndWait();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to save the Parent", ButtonType.OK).showAndWait();
        }


        TableUpdate();
        ClearTextFeilds();
    }

    public void selectTableRaw(MouseEvent mouseEvent) {
        GurdianData selectedParent = tblGurdianDetails.getSelectionModel().getSelectedItem();
        txtWorkingPlace.setText(selectedParent.getWorkPlace());
        txtTelephone.setText(selectedParent.getTelephone());
        txtParentName.setText(selectedParent.getName());
        txtMobile1.setText(selectedParent.getMobile());
        txtEmail.setText(selectedParent.getEmail());
        txtDesignation.setText(selectedParent.getDesignation());
        txtMoble2.setText(selectedParent.getMobile());
        txtAddress.setText(selectedParent.getAddress());
    }
}
