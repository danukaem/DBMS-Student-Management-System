package Business.Custom.Impl;

import Business.Converter;
import Business.Custom.ManageParentBO;
import Dao.Custom.Impl.ParentDaoImpl;
import Dao.DAOFactory;
import Dto.ParentDto;
import Entity.Parent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class ManageParentBOImpl implements ManageParentBO {
    private static ParentDaoImpl parentDao = (ParentDaoImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.Parent);


    @Override
    public List<ParentDto> getParents() throws SQLException {
        List<Parent> allCustomers = parentDao.findAll();
        List<ParentDto> tmpDTOs = new ArrayList<>();
        for (Parent parent : allCustomers) {
            ParentDto dto = new ParentDto(parent.getStudentID(), parent.getName(), parent.getTelephone(),parent.getMobile()
                    , parent.getMobile2(), parent.getEmail(), parent.getDesignation(), parent.getWorkPlace(), parent.getAddress());
            tmpDTOs.add(dto);
        }
        return tmpDTOs;

    }

    @Override
    public boolean createParents(ParentDto dto) throws SQLException {

        return parentDao.save(Converter.getEntity(dto));
    }

    @Override
    public boolean updateParents(ParentDto dto) throws SQLException {
        return parentDao.update(Converter.getEntity(dto));
    }

    @Override
    public boolean deleteParents(String parentID) throws SQLException {
        return parentDao.delete(parentID);
    }


}
