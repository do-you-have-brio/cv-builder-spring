package dyhb.api.mappers;

import dyhb.api.database.models.JobModel;
import dyhb.api.dto.JobUpsertDto;
import java.util.*;
import java.util.stream.Collectors;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface JobMapper {
  JobMapper INSTANCE = Mappers.getMapper(JobMapper.class);

  JobModel fromCreateDtoToModel(JobUpsertDto dto, UUID userId);

  default List<JobModel> fromCreateDtosToModels(List<JobUpsertDto> dtos, UUID userId) {
    return dtos == null
        ? List.of()
        : dtos.stream().map(dto -> fromCreateDtoToModel(dto, userId)).collect(Collectors.toList());
  }

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "userId", ignore = true)
  JobModel updateModelFromDto(JobUpsertDto dto, @MappingTarget JobModel model);
}
