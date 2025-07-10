package dyhb.api.mappers;

import dyhb.api.database.models.SocialAccountModel;
import java.util.*;
import java.util.stream.Collectors;
import dyhb.api.dto.SocialAccountUpsertDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SocialAccountMapper {
  SocialAccountMapper INSTANCE = Mappers.getMapper(SocialAccountMapper.class);

  SocialAccountModel fromCreateDtoToModel(SocialAccountUpsertDto dto, UUID userId);

  default List<SocialAccountModel> fromCreateDtosToModels(
      List<SocialAccountUpsertDto> dtos, UUID userId) {
    return dtos == null
        ? List.of()
        : dtos.stream().map(dto -> fromCreateDtoToModel(dto, userId)).collect(Collectors.toList());
  }

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "userId", ignore = true)
  SocialAccountModel updateModelFromDto(
      SocialAccountUpsertDto dto, @MappingTarget SocialAccountModel model);
}
