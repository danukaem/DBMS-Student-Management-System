package Dao.Custom.Impl;

import DB.DBConnection;
import Dao.Custom.ParentDao;
import Entity.Course;
import Entity.Parent;
import controller.RegisterStudentController;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import view.Util.GurdianData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParentDaoImpl implements ParentDao {

    @Override
    public Parent find(String studentID) throws SQLException {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM gurdian WHERE studentid=?");
        pstm.setObject(1, studentID);
        ResultSet rst = pstm.executeQuery();
        if (rst.next()) {
            return new Parent(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4)
                    , rst.getString(5), rst.getString(6), rst.getString(7), rst.getString(8), rst.getString(9));
        }
        return null;

    }

    @Override
    public List<Parent> findAll() throws SQLException {
        List<Parent> arraylist = new ArrayList<>();

        Connection connection = DBConnection.getConnection();
        PreparedStatement scfc = connection.prepareStatement("select * from gurdian");
        ResultSet resultSet = scfc.executeQuery();
        while (resultSet.next()) {
            arraylist.add(new Parent(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
                    resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7)
                    , resultSet.getString(8), resultSet.getString(9)));
        }
        return arraylist;
    }


    @Override
    public boolean save(Parent parent) throws SQLException {
        Connection connection = DBConnection.getConnection();
        PreparedStatement scfc = connection.prepareStatement("insert into gurdian values (?,?,?,?,?,?,?,?,?)");
        scfc.setObject(1, parent.getStudentID());
        scfc.setObject(2, parent.getName());
        scfc.setObject(3, parent.getTelephone());
        scfc.setObject(4, parent.getMobile());
        scfc.setObject(5, parent.getMobile2());
        scfc.setObject(6, parent.getEmail());
        scfc.setObject(7, parent.getDesignation());
        scfc.setObject(8, parent.getWorkPlace());
        scfc.setObject(9, parent.getAddress());
        return scfc.executeUpdate() > 0;


    }

    @Override
    public boolean update(Parent parent) throws SQLException {

        Connection connection = DBConnection.getConnection();
        PreparedStatement scfc = connection.prepareStatement("UPDATE gurdian set telnumber=?,mob1number=?,mob2number=?,email=?," +
                "designation=?,workingplace=?,address =? where gurdiantname=? ");
        scfc.setObject(1, parent.getTelephone());
        scfc.setObject(2, parent.getMobile());
        scfc.setObject(3, parent.getMobile2());
        scfc.setObject(4, parent.getEmail());
        scfc.setObject(5, parent.getDesignation());
        scfc.setObject(6, parent.getWorkPlace());
        scfc.setObject(7, parent.getAddress());
        scfc.setObject(8, parent.getName());

        return scfc.executeUpdate() > 0;

    }

    @Override
    public boolean delete(String parent) throws SQLException {

        Connection connection = DBConnection.getConnection();
        PreparedStatement scfc = connection.prepareStatement("delete from gurdian where gurdiantname=?");
        scfc.setObject(1, parent);
        return scfc.executeUpdate() > 0;
    }
}
