package dyhb.api.mappers;

import dyhb.api.database.models.ResumeModel;
import dyhb.api.dto.ResumeUpsertDto;
import java.util.UUID;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ResumeMapper {
  ResumeMapper INSTANCE = Mappers.getMapper(ResumeMapper.class);

  ResumeModel fromCreateDtoToModel(ResumeUpsertDto dto, UUID userId);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "userId", ignore = true)
  ResumeModel updateModelFromDto(ResumeUpsertDto dto, @MappingTarget ResumeModel model);
}
