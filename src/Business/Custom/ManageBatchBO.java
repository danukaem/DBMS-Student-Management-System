package Business.Custom;

import Business.SuperBO;
import Dto.BatchDto;
import Dto.CourseDto;

import java.sql.SQLException;
import java.util.List;

public interface ManageBatchBO extends SuperBO {
    String getCourseID(String courseName)throws SQLException;
    List<BatchDto> getBatch() throws SQLException;

    boolean createBatch(BatchDto dto) throws SQLException;

    boolean updateBatch(BatchDto dto) throws SQLException;

    boolean deleteBatch(String customerID) throws SQLException;

    BatchDto findBatch(String Cid) throws SQLException;
}
