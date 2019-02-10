package Business;

import Dto.BatchDto;
import Dto.CourseDto;
import Dto.ParentDto;
import Dto.SuperDTO;
import Entity.Batch;
import Entity.Course;
import Entity.Parent;
import Entity.SuperEntity;

import java.util.List;
import java.util.stream.Collectors;

public class Converter {
    public static <T extends SuperDTO> T getDTO(SuperEntity entity) {
        if (entity instanceof Course) {
            Course c = (Course) entity;
            return (T) new CourseDto(c.getCourseId(), c.getCourseName(), c.getCourseDescription(),c.getDuration());
        } else if (entity instanceof Batch) {
            Batch i = (Batch) entity;
            return (T) new BatchDto(i.getCourse(), i.getBatchId(), i.getDate(), i.getDescription(),i.getBatchCapacity(),i.getCourseID());
        } else {
            throw new RuntimeException("This entity can't be converted to a DTO");
        }
    }

    public static <T extends SuperEntity> T getEntity(SuperDTO dto) {
        if (dto instanceof CourseDto) {
            CourseDto c = (CourseDto) dto;
            return (T) new Course(c.getCourseId(), c.getCourseName(), c.getCourseDescription(),c.getDuration());
        } else if (dto instanceof BatchDto) {
            BatchDto i = (BatchDto) dto;
            return (T) new Batch(i.getCourse(), i.getBatchId(), i.getDate(), i.getDescription(),i.getBatchCapacity(),i.getCourseID());
        }else if (dto instanceof ParentDto) {
            ParentDto i = (ParentDto) dto;
            return (T) new Parent(i.getStudentID(), i.getName(), i.getTelephone(), i.getMobile(),i.getMobile2(),i.getEmail(),
                    i.getDesignation(), i.getWorkPlace(), i.getAddress());
        } else {
            throw new RuntimeException("This DTO can't be converted to an entity");
        }
    }

    public static <T extends SuperDTO> List<T> getDTOList(List<? extends SuperEntity> entities) {
        return entities.stream().map(Converter::<T>getDTO).collect(Collectors.toList());
    }

    public static <T extends SuperEntity> List<T> getEntityList(List<? extends SuperDTO> dtos) {
        return dtos.stream().map(Converter::<T>getEntity).collect(Collectors.toList());

    }



}
