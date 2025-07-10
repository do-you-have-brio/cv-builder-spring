package dyhb.api.mappers;

import dyhb.api.database.models.EducationModel;
import dyhb.api.dto.CreateEducationDTO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper(
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EducationMapper {

  EducationMapper INSTANCE = Mappers.getMapper(EducationMapper.class);
  EducationModel fromCreateDTOtoModel(CreateEducationDTO dto, UUID userId);
}
