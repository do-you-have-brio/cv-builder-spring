package dyhb.api.mappers;

import dyhb.api.database.models.EmploymentModel;
import dyhb.api.database.models.enums.EmploymentType;
import dyhb.api.dto.EmploymentUpsertDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.*;

public class EmploymentMapperTest {

  private final EmploymentMapper mapper = Mappers.getMapper(EmploymentMapper.class);

  @Test
  void fromCreateDtoToModel() {
    UUID userId = UUID.randomUUID();
    EmploymentUpsertDto dto =
        EmploymentUpsertDto.builder()
            .title("Software Engineer")
            .company("Tech Company")
            .location("New York")
            .employmentType(EmploymentType.FULL_TIME)
            .skills(List.of("Java", "Spring"))
            .description("Developing software solutions")
            .startDate(new Date())
            .endDate(new Date())
            .build();

    EmploymentModel model = mapper.fromCreateDtoToModel(dto, userId);

    Assertions.assertNotNull(model);
    Assertions.assertEquals(dto.getTitle(), model.getTitle());
    Assertions.assertEquals(dto.getCompany(), model.getCompany());
    Assertions.assertEquals(dto.getLocation(), model.getLocation());
    Assertions.assertEquals(dto.getEmploymentType(), model.getEmploymentType());
    Assertions.assertEquals(dto.getSkills(), model.getSkills());
    Assertions.assertEquals(dto.getDescription(), model.getDescription());
    Assertions.assertEquals(dto.getStartDate(), model.getStartDate());
    Assertions.assertEquals(dto.getEndDate(), model.getEndDate());
    Assertions.assertNull(model.getId());
    Assertions.assertEquals(userId, model.getUserId());
  }

  @Test
  void fromCreateDtosToModels() {
    UUID userId = UUID.randomUUID();
    List<EmploymentUpsertDto> dtos =
        List.of(
            EmploymentUpsertDto.builder()
                .title("Software Engineer")
                .company("Tech Company")
                .location("New York")
                .employmentType(EmploymentType.FULL_TIME)
                .skills(List.of("Java", "Spring"))
                .description("Developing software solutions")
                .startDate(new Date())
                .endDate(new Date())
                .build());

    List<EmploymentModel> models = mapper.fromCreateDtosToModels(dtos, userId);

    Assertions.assertEquals(dtos.size(), models.size(), "Should map all DTOs to models");

    EmploymentModel model = models.getFirst();
    EmploymentUpsertDto dto = dtos.getFirst();

    Assertions.assertEquals(dto.getTitle(), model.getTitle());
    Assertions.assertEquals(dto.getCompany(), model.getCompany());
    Assertions.assertEquals(dto.getLocation(), model.getLocation());
    Assertions.assertEquals(dto.getEmploymentType(), model.getEmploymentType());
    Assertions.assertEquals(dto.getSkills(), model.getSkills());
    Assertions.assertEquals(dto.getDescription(), model.getDescription());
    Assertions.assertEquals(dto.getStartDate(), model.getStartDate());
    Assertions.assertEquals(dto.getEndDate(), model.getEndDate());
    Assertions.assertNull(model.getId());
    Assertions.assertEquals(userId, model.getUserId());
  }

  @Test
  void updateModelFromDto() {
    EmploymentModel model = new EmploymentModel();
    model.setId(UUID.randomUUID());
    model.setUserId(UUID.randomUUID());
    model.setTitle("Original Title");
    model.setCompany("Original Company");
    model.setLocation("Original Location");
    model.setEmploymentType(EmploymentType.PART_TIME);
    model.setSkills(new ArrayList<>(List.of("Original Skill")));
    model.setDescription("Original Description");
    model.setStartDate(new Date());
    model.setEndDate(null);

    EmploymentUpsertDto dto =
        EmploymentUpsertDto.builder()
            .title("Updated Title")
            .company("Updated Company")
            .location("Updated Location")
            .employmentType(EmploymentType.FULL_TIME)
            .skills(new ArrayList<>(List.of("Updated Skill 1", "Updated Skill 2")))
            .description("Updated Description")
            .endDate(new Date())
            .build();

    EmploymentModel updatedModel = mapper.updateModelFromDto(dto, model);

    Assertions.assertSame(model, updatedModel);
    Assertions.assertEquals(model.getId(), updatedModel.getId());
    Assertions.assertEquals(model.getUserId(), updatedModel.getUserId());
    Assertions.assertEquals(dto.getTitle(), updatedModel.getTitle());
    Assertions.assertEquals(dto.getCompany(), updatedModel.getCompany());
    Assertions.assertEquals(dto.getLocation(), updatedModel.getLocation());
    Assertions.assertEquals(dto.getEmploymentType(), updatedModel.getEmploymentType());
    Assertions.assertEquals(dto.getSkills(), updatedModel.getSkills());
    Assertions.assertEquals(dto.getDescription(), updatedModel.getDescription());
    Assertions.assertEquals(model.getStartDate(), updatedModel.getStartDate());
    Assertions.assertEquals(dto.getEndDate(), updatedModel.getEndDate());
  }
}
