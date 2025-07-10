package dyhb.api.mappers;

import dyhb.api.database.models.EducationModel;
import dyhb.api.dto.EducationUpsertDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper(
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EducationMapper {
  EducationMapper INSTANCE = Mappers.getMapper(EducationMapper.class);

  EducationModel fromCreateDtoToModel(EducationUpsertDto dto, UUID userId);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "userId", ignore = true)
  EducationModel updateModelFromDto(EducationUpsertDto dto, @MappingTarget EducationModel model);
}
