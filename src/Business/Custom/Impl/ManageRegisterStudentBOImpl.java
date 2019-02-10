package Business.Custom.Impl;

import Business.Custom.ManageRegisterStudentBO;
import DB.DBConnection;
import Dto.StudentRegisterDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ManageRegisterStudentBOImpl implements ManageRegisterStudentBO {

    ArrayList<StudentRegisterDto> studentDetailsarray = new ArrayList<>();

    @Override
    public List<StudentRegisterDto> getRegStudent() throws SQLException {

        Connection connection = DBConnection.getConnection();
        PreparedStatement scfc = connection.prepareStatement("select r.studentid,s.fullname,r.batchid,r.regDate  from registration r\n" +
                "inner join studentdetails s on r.studentid = s.studentid  group by r.studentid");
        ResultSet rst = scfc.executeQuery();
        while (rst.next()) {
            LocalDate localDate = rst.getDate(4).toLocalDate();
            studentDetailsarray.add(new StudentRegisterDto(rst.getString(1), rst.getString(2), rst.getString(3), localDate));
        }
        return studentDetailsarray;
    }

    @Override
    public boolean createRegStudent(StudentRegisterDto dto) throws SQLException {
        return false;
    }

    @Override
    public boolean updateRegStudent(StudentRegisterDto dto) throws SQLException {
        return false;
    }

    @Override
    public boolean deleteRegStudent(String studentID) throws SQLException {
        return false;
    }

    @Override
    public StudentRegisterDto findRegStudent(String Sid) throws SQLException {
        return null;
    }


}
