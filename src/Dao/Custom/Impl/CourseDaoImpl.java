package Dao.Custom.Impl;

import DB.DBConnection;
import Dao.Custom.CourseDao;
import Entity.Course;
import javafx.event.ActionEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDaoImpl implements CourseDao {



    @Override
    public Course find(String courseId) throws SQLException {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM courses WHERE courseid=?");
        pstm.setObject(1, courseId);
        ResultSet rst = pstm.executeQuery();
        if (rst.next()){
            return  new Course(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4));
        }
        return null;
    }

    public List<Course> findAll() throws SQLException {
        ArrayList<Course> alCustomerS = new ArrayList<>();
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM courses");
        ResultSet rst = pstm.executeQuery();
        while (rst.next()) {
            String id = rst.getString(1);
            String name = rst.getString(2);
            String description = rst.getString(3);
            int duration = rst.getInt(4);

            Course customer = new Course(id, name, description,duration);
            alCustomerS.add(customer);
        }
        return alCustomerS;
    }

    public boolean save(Course course) throws SQLException {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO courses VALUES (?,?,?,?)");
        pstm.setObject(1, course.getCourseId());
        pstm.setObject(2, course.getCourseName());
        pstm.setObject(3, course.getCourseDescription());
        pstm.setObject(4, course.getDuration());

        return pstm.executeUpdate() > 0;
    }

    public boolean update(Course course) throws SQLException {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("UPDATE courses SET coursename=?,coursedescription=?,courseduration=? WHERE courseid=?");
        pstm.setObject(3, course.getDuration());
        pstm.setObject(1, course.getCourseName());
        pstm.setObject(2, course.getCourseDescription());
        pstm.setObject(4, course.getCourseId());

        return pstm.executeUpdate() > 0;
    }

    public boolean delete(String courseId) throws SQLException {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM courses WHERE courseid=?");
        pstm.setObject(1, courseId);
        return pstm.executeUpdate() > 0;
    }
}
