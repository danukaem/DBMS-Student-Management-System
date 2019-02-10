package Business.Custom.Impl;

import Business.Converter;
import Business.Custom.ManageBatchBO;
import Business.Custom.ManageCourseBO;
import Dao.Custom.CourseDao;
import Dao.DAOFactory;
import Dto.CourseDto;
import Entity.Course;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManageCourseBOImpl implements ManageCourseBO {
    private static CourseDao courseDao = (CourseDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.Course);

    public  List<CourseDto> getCourse() throws SQLException {
        List<Course> allCustomers = courseDao.findAll();
        List<CourseDto> tmpDTOs = new ArrayList<>();
        for (Course course : allCustomers) {
            CourseDto dto = new CourseDto(course.getCourseId(), course.getCourseName(), course.getCourseDescription(),course.getDuration());
            tmpDTOs.add(dto);
        }
        return tmpDTOs;
    }

    public  boolean createCourse(CourseDto dto) throws SQLException {
          return courseDao.save(Converter.getEntity(dto));
    }

    public  boolean updateCourse(CourseDto dto) throws SQLException {
        return courseDao.update(Converter.getEntity(dto));
    }

    public  boolean deleteCourse(String customerID) throws SQLException {
        return courseDao.delete(customerID);
    }

    public  CourseDto findCourse(String Cid) throws SQLException {
        Course course = courseDao.find(Cid);
        if (course != null) {
            return new CourseDto(course.getCourseId(),
                    course.getCourseName(),
                    course.getCourseDescription(),course.getDuration());
        }
        return null;
    }


}
