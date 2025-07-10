package dyhb.api.mappers;

import dyhb.api.database.models.ProfileModel;
import dyhb.api.dto.ProfileUpsertDto;
import java.util.*;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProfileMapper {
  ProfileMapper INSTANCE = Mappers.getMapper(ProfileMapper.class);

  ProfileModel fromCreateDtoToModel(ProfileUpsertDto dto, UUID userId);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "userId", ignore = true)
  ProfileModel updateModelFromDto(ProfileUpsertDto dto, @MappingTarget ProfileModel model);
}
