package dyhb.api.mappers;

import dyhb.api.database.models.EducationModel;
import dyhb.api.dto.EducationUpsertDto;
import java.util.*;
import java.util.stream.Collectors;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface EducationMapper {
    EducationMapper INSTANCE = Mappers.getMapper(EducationMapper.class);

    EducationModel fromCreateDtoToModel(EducationUpsertDto dto, UUID userId);

    default List<EducationModel> fromCreateDtosToModels(
        List<EducationUpsertDto> dtos,
        UUID userId
    ) {
        return dtos == null
            ? List.of()
            : dtos
                .stream()
                .map(dto -> fromCreateDtoToModel(dto, userId))
                .collect(Collectors.toList());
    }

    EducationModel updateModelFromDto(
        EducationUpsertDto dto,
        @MappingTarget EducationModel model
    );
}
