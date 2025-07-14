package dyhb.api.mappers;

import dyhb.api.database.models.ProjectModel;
import dyhb.api.dto.ProjectUpsertDto;
import java.util.*;
import java.util.stream.Collectors;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProjectMapper {
  ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

  ProjectModel fromCreateDtoToModel(ProjectUpsertDto dto, UUID userId);

  default List<ProjectModel> fromCreateDtosToModels(List<ProjectUpsertDto> dtos, UUID userId) {
    return dtos == null
        ? List.of()
        : dtos.stream().map(dto -> fromCreateDtoToModel(dto, userId)).collect(Collectors.toList());
  }

  ProjectModel updateModelFromDto(ProjectUpsertDto dto, @MappingTarget ProjectModel model);
}
