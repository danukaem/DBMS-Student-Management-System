package Business.Custom;

import Business.SuperBO;
import Dto.CourseDto;
import Dto.StudentRegisterDto;

import java.sql.SQLException;
import java.util.List;

public interface ManageRegisterStudentBO extends SuperBO {

    List<StudentRegisterDto> getRegStudent() throws SQLException;

    boolean createRegStudent(StudentRegisterDto dto) throws SQLException;

    boolean updateRegStudent(StudentRegisterDto dto) throws SQLException;

    boolean deleteRegStudent(String studentID) throws SQLException;

    StudentRegisterDto findRegStudent(String Sid) throws SQLException;
}
