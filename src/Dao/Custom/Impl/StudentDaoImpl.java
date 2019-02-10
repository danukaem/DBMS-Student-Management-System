package Dao.Custom.Impl;

import Dao.Custom.StudentDao;
import Entity.Student;

import java.sql.SQLException;
import java.util.List;

public class StudentDaoImpl  implements StudentDao {
    @Override
    public Student find(String key) throws SQLException {
        return null;
    }

    @Override
    public List<Student> findAll() throws SQLException {
        return null;
    }

    @Override
    public boolean save(Student entity) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Student entity) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String key) throws SQLException {
        return false;
    }
}
