package Dao.Custom.Impl;

import Dao.Custom.ProfessionalQualificationDao;
import Entity.Parent;
import javafx.event.ActionEvent;

import java.sql.SQLException;
import java.util.List;

public class ProfessionalQualificationDaoImpl implements ProfessionalQualificationDao {

    @Override
    public Parent find(String key) throws SQLException {
        return null;
    }

    @Override
    public List<Parent> findAll() throws SQLException {
        return null;
    }

    @Override
    public boolean save(Parent entity) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Parent entity) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String key) throws SQLException {
        return false;
    }
}
