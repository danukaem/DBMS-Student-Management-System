package Dao;

import Dao.Custom.Impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    public enum DAOTypes {
        Course, Batch, Parent, ProfessionalQualification, QUERY, Student;
    }


    private DAOFactory() {

    }

    public static DAOFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    public SuperDAO getDAO(DAOTypes daoType) {
        switch (daoType) {
            case Course:
                return new CourseDaoImpl();
            case Batch:
                return new BatchDaoImpl();
            case Parent:
                return new ParentDaoImpl();
            case ProfessionalQualification:
                return new ProfessionalQualificationDaoImpl();
            case QUERY:
                return new QuaryDaoImpl();
            case Student:
                return new StudentDaoImpl();
            default:
                return null;
        }
    }
}
