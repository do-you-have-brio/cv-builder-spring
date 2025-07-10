package dyhb.api.mappers;

import dyhb.api.database.models.EducationModel;
import dyhb.api.database.models.enums.EducationDegree;
import dyhb.api.dto.EducationUpsertDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.*;

public class EducationMapperTest {

  private final EducationMapper mapper = Mappers.getMapper(EducationMapper.class);

  @Test
  void fromCreateDtoToModel() {
    UUID userId = UUID.randomUUID();
    EducationUpsertDto dto =
        EducationUpsertDto.builder()
            .name("Computer Science")
            .educationDegree(EducationDegree.BACHELOR)
            .institution("Tech University")
            .description("Bachelor's degree in Computer Science")
            .startDate(new Date())
            .endDate(new Date())
            .build();

    EducationModel model = mapper.fromCreateDtoToModel(dto, userId);

    Assertions.assertNotNull(model);
    Assertions.assertEquals(dto.getName(), model.getName());
    Assertions.assertEquals(dto.getEducationDegree(), model.getEducationDegree());
    Assertions.assertEquals(dto.getInstitution(), model.getInstitution());
    Assertions.assertEquals(dto.getDescription(), model.getDescription());
    Assertions.assertEquals(dto.getStartDate(), model.getStartDate());
    Assertions.assertEquals(dto.getEndDate(), model.getEndDate());
    Assertions.assertNull(model.getId());
    Assertions.assertEquals(userId, model.getUserId());
  }

  @Test
  void fromCreateDtosToModels() {
    UUID userId = UUID.randomUUID();
    List<EducationUpsertDto> dtos =
        List.of(
            EducationUpsertDto.builder()
                .name("Computer Science")
                .educationDegree(EducationDegree.BACHELOR)
                .institution("Tech University")
                .description("Bachelor's degree in Computer Science")
                .startDate(new Date())
                .endDate(new Date())
                .build());

    List<EducationModel> models = mapper.fromCreateDtosToModels(dtos, userId);

    Assertions.assertEquals(dtos.size(), models.size(), "Should map all DTOs to models");

    EducationModel model = models.getFirst();
    EducationUpsertDto dto = dtos.getFirst();

    Assertions.assertEquals(dto.getName(), model.getName());
    Assertions.assertEquals(dto.getEducationDegree(), model.getEducationDegree());
    Assertions.assertEquals(dto.getInstitution(), model.getInstitution());
    Assertions.assertEquals(dto.getDescription(), model.getDescription());
    Assertions.assertEquals(dto.getStartDate(), model.getStartDate());
    Assertions.assertEquals(dto.getEndDate(), model.getEndDate());
    Assertions.assertNull(model.getId());
    Assertions.assertEquals(userId, model.getUserId());
  }

  @Test
  void updateModelFromDto() {
    EducationModel model = new EducationModel();
    model.setId(UUID.randomUUID());
    model.setUserId(UUID.randomUUID());
    model.setName("Original Name");
    model.setEducationDegree(EducationDegree.HIGH_SCHOOL);
    model.setInstitution("Original Institution");
    model.setDescription("Original Description");
    model.setStartDate(new Date());
    model.setEndDate(null);

    EducationUpsertDto dto =
        EducationUpsertDto.builder()
            .name("Updated Name")
            .educationDegree(EducationDegree.CERTIFICATE)
            .institution("Updated Institution")
            .description("Updated Description")
            .endDate(new Date())
            .build();

    EducationModel updatedModel = mapper.updateModelFromDto(dto, model);

    Assertions.assertSame(model, updatedModel);
    Assertions.assertEquals(model.getId(), updatedModel.getId());
    Assertions.assertEquals(model.getUserId(), updatedModel.getUserId());
    Assertions.assertEquals(dto.getName(), updatedModel.getName());
    Assertions.assertEquals(dto.getEducationDegree(), updatedModel.getEducationDegree());
    Assertions.assertEquals(dto.getInstitution(), updatedModel.getInstitution());
    Assertions.assertEquals(dto.getDescription(), updatedModel.getDescription());
    Assertions.assertEquals(model.getStartDate(), updatedModel.getStartDate());
    Assertions.assertEquals(dto.getEndDate(), updatedModel.getEndDate());
  }
}
