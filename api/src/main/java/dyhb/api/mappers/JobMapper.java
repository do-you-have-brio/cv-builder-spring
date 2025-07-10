package dyhb.api.mappers;

import dyhb.api.database.models.JobModel;
import dyhb.api.dto.JobUpsertDto;
import java.util.UUID;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface JobMapper {
  JobMapper INSTANCE = Mappers.getMapper(JobMapper.class);

  JobModel fromCreateDtoToModel(JobUpsertDto dto, UUID userId);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "userId", ignore = true)
  JobModel updateModelFromDto(JobUpsertDto dto, @MappingTarget JobModel model);
}
