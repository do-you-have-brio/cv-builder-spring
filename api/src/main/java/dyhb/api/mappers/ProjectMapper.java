package dyhb.api.mappers;

import dyhb.api.database.models.ProjectModel;
import dyhb.api.dto.ProjectUpsertDto;
import java.util.UUID;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProjectMapper {
  ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

  ProjectModel fromCreateDtoToModel(ProjectUpsertDto dto, UUID userId);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "userId", ignore = true)
  ProjectModel updateModelFromDto(ProjectUpsertDto dto, @MappingTarget ProjectModel model);
}
