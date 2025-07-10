package dyhb.api.mappers;

import dyhb.api.database.models.ProjectModel;
import dyhb.api.dto.CreateProjectDTO;
import java.util.UUID;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProjectMapper {

  ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);
  ProjectModel fromCreateDTOtoModel(CreateProjectDTO dto, UUID userId);
}
