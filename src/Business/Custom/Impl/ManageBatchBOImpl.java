package Business.Custom.Impl;

import Business.Converter;
import Business.Custom.ManageBatchBO;
import DB.DBConnection;
import Dao.Custom.BatchDao;
import Dao.DAOFactory;
import Dto.BatchDto;
import Dto.CourseDto;
import Entity.Batch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManageBatchBOImpl implements ManageBatchBO {
    private static BatchDao batchDao = (BatchDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.Batch);

    public String getCourseID(String courseName)throws SQLException{
        String courseId;
        Connection connection = DBConnection.getConnection();
        PreparedStatement selectId1 = connection.prepareStatement("select * from batches  where coursename=?");
        selectId1.setObject(1, courseName);
        ResultSet resultSet1 = selectId1.executeQuery();
        if (resultSet1.next()) {
            courseId = resultSet1.getString(1);
            return courseId;
        }
        return null;

    }

    public List<BatchDto> getBatch() throws SQLException {
        List<Batch> allBatches = batchDao.findAll();
        List<BatchDto> tmpDTOs = new ArrayList<>();
        for (Batch batch : allBatches) {
            //BatchDto(String course, String batchId, LocalDate date, String description, int batchCapacity, String courseID)
            BatchDto dto = new BatchDto(batch.getCourse(), batch.getBatchId(), batch.getDate()
                    ,batch.getDescription(),batch.getBatchCapacity(),batch.getCourseID());
            tmpDTOs.add(dto);


        }
        return tmpDTOs;
    }

    public  boolean createBatch(BatchDto dto) throws SQLException {
        return batchDao.save(Converter.getEntity(dto));


    }

    public  boolean updateBatch(BatchDto dto) throws SQLException {
        return batchDao.update(Converter.getEntity(dto));
    }

    public  boolean deleteBatch(String customerID) throws SQLException {
        return batchDao.delete(customerID);
    }

    public  BatchDto findBatch(String Bid) throws SQLException {
        Batch batch = batchDao.find(Bid);
        if (batch != null) {
            return new BatchDto(batch.getCourse(), batch.getBatchId(), batch.getDate()
                    ,batch.getDescription(),batch.getBatchCapacity(),batch.getCourseID());
        }
        return null;
    }


}
