package dyhb.api.mappers;

import dyhb.api.database.models.EmploymentModel;
import dyhb.api.dto.EmploymentUpsertDto;
import java.util.*;
import java.util.stream.Collectors;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmploymentMapper {
  EmploymentMapper INSTANCE = Mappers.getMapper(EmploymentMapper.class);

  EmploymentModel fromCreateDtoToModel(EmploymentUpsertDto dto, UUID userId);

  default List<EmploymentModel> fromCreateDtosToModels(
      List<EmploymentUpsertDto> dtos, UUID userId) {
    return dtos == null
        ? List.of()
        : dtos.stream().map(dto -> fromCreateDtoToModel(dto, userId)).collect(Collectors.toList());
  }

  EmploymentModel updateModelFromDto(EmploymentUpsertDto dto, @MappingTarget EmploymentModel model);
}
