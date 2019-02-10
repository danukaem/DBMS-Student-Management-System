package Business.Custom;

import Business.SuperBO;
import Dto.CourseDto;
import Dto.ParentDto;

import java.sql.SQLException;
import java.util.List;

public interface ManageParentBO extends SuperBO {

    List<ParentDto> getParents() throws SQLException;

    boolean createParents(ParentDto dto) throws SQLException;

    boolean updateParents(ParentDto dto) throws SQLException;

    boolean deleteParents(String customerID) throws SQLException;

}
