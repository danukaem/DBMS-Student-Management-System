package Dao.Custom.Impl;

import Business.Custom.Impl.ManageCourseBOImpl;
import DB.DBConnection;
import Dao.Custom.BatchDao;
import Entity.Batch;
import Entity.Course;
import controller.ManageBatchController;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BatchDaoImpl implements BatchDao {

    ManageBatchController mn = new ManageBatchController();

    @Override
    public Batch find(String batchId) throws SQLException {
        Connection connection = DBConnection.getConnection();
        PreparedStatement selectId1 = connection.prepareStatement("select * from batches  where batchid=?");
        selectId1.setObject(1, batchId);
        ResultSet resultSet1 = selectId1.executeQuery();
        if (resultSet1.next()) {
            LocalDate date = resultSet1.getDate(4).toLocalDate();
            return  new Batch(resultSet1.getString(2), resultSet1.getString(3), date,
                    resultSet1.getString(5), resultSet1.getInt(6), resultSet1.getString(1));
        }
        return null;

    }


    @Override
    public List<Batch> findAll() throws SQLException {
        ArrayList<Batch> alBatch = new ArrayList();
        Connection connection = DBConnection.getConnection();
        PreparedStatement selectId1 = connection.prepareStatement("select * from batches ");
        ResultSet resultSet1 = selectId1.executeQuery();
        while (resultSet1.next()) {
            LocalDate date = resultSet1.getDate(4).toLocalDate();
            alBatch.add(new Batch(resultSet1.getString(2), resultSet1.getString(3), date,
                    resultSet1.getString(5), resultSet1.getInt(6), resultSet1.getString(1)));
        }
        return alBatch;
    }

    @Override
    public boolean save(Batch batch) throws SQLException {
        Connection connection = DBConnection.getConnection();
        String courseName = batch.getCourse();
        PreparedStatement selectId1 = connection.prepareStatement("select * from courses where coursename=?");
        selectId1.setObject(1, courseName);
        ResultSet resultSet1 = selectId1.executeQuery();
        String courseId = null;
        while (resultSet1.next()) {
            courseId = resultSet1.getString(1);
        }

        PreparedStatement selectId = connection.prepareStatement("select * from batches where batchid=?");
        selectId.setObject(1, batch.getBatchId());
        ResultSet resultSet = selectId.executeQuery();
        if (resultSet.next()) {
            new Alert(Alert.AlertType.WARNING, "This Batch id Already Exist", ButtonType.OK).show();
            ManageBatchController mn = new ManageBatchController();
            mn.ClearTextFeilds();
            return false;

        } else {
            PreparedStatement scfc = connection.prepareStatement("insert into batches values (?,?,?,?,?,?)");

            scfc.setObject(1, courseId);
            scfc.setObject(2, courseName);
            scfc.setObject(3, batch.getBatchId());
            scfc.setObject(4, batch.getDate());
            scfc.setObject(5, batch.getDescription());
            scfc.setObject(6, batch.getBatchCapacity());


            int rslt = scfc.executeUpdate();
            if (rslt > 0) {
                new Alert(Alert.AlertType.CONFIRMATION, "Successful ", ButtonType.OK).show();
                return true;
            } else {
                new Alert(Alert.AlertType.WARNING, "Unsuccessful", ButtonType.OK).show();
                return false;
            }

        }


    }

    @Override
    public boolean update(Batch batch) throws SQLException {
        Connection connection = DBConnection.getConnection();
        PreparedStatement scfc = connection.prepareStatement("UPDATE batches set batchstartingdate=?,batchDescription=?,batchcapacity=?,batchid=?,coursename=? where batchid=? ");
        scfc.setObject(1, batch.getDate());
        scfc.setObject(2, batch.getDescription());
        scfc.setObject(3, batch.getBatchCapacity());
        scfc.setObject(4, batch.getBatchId());
        scfc.setObject(5, batch.getCourse());
        scfc.setObject(6, batch.getBatchId());
        return scfc.executeUpdate()>0;


    }

    @Override
    public boolean delete(String batchId) throws SQLException {

        Connection connection = DBConnection.getConnection();
        PreparedStatement scfc = connection.prepareStatement("delete from batches where batchid=?");
        scfc.setObject(1, batchId);
        int i = scfc.executeUpdate();
        if (i > 0) {
            new Alert(Alert.AlertType.CONFIRMATION, "Successful ", ButtonType.OK).show();
            return true;
        } else {
            new Alert(Alert.AlertType.WARNING, "Unsuccessful", ButtonType.OK).show();
            return false;
        }

    }
}

