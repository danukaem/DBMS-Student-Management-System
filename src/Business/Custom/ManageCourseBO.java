package Business.Custom;

import Business.SuperBO;
import Dto.CourseDto;
import Entity.Course;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ManageCourseBO extends SuperBO {
    List<CourseDto> getCourse() throws SQLException;

    boolean createCourse(CourseDto dto) throws SQLException;

    boolean updateCourse(CourseDto dto) throws SQLException;

    boolean deleteCourse(String customerID) throws SQLException;

    CourseDto findCourse(String Cid) throws SQLException;

}
