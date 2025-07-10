package dyhb.api.mappers;

import dyhb.api.database.models.ResumeModel;
import dyhb.api.dto.CreateResumeDTO;
import java.util.UUID;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ResumeMapper {

  ResumeMapper INSTANCE = Mappers.getMapper(ResumeMapper.class);
  ResumeModel fromCreateDTOtoModel(CreateResumeDTO dto, UUID userId);
}
