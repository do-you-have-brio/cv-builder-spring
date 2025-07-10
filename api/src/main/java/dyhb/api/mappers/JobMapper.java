package dyhb.api.mappers;

import dyhb.api.database.models.JobModel;
import dyhb.api.dto.CreateJobDTO;
import java.util.UUID;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface JobMapper {

  JobMapper INSTANCE = Mappers.getMapper(JobMapper.class);
  JobModel fromCreateDTOtoModel(CreateJobDTO dto, UUID userId);
}
