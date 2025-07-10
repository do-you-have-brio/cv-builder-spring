package dyhb.api.mappers;

import dyhb.api.database.models.ProjectModel;
import dyhb.api.dto.ProjectUpsertDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.*;

public class ProjectMapperTest {

  private final ProjectMapper mapper = Mappers.getMapper(ProjectMapper.class);

  @Test
  void fromCreateDtoToModel() {
    UUID userId = UUID.randomUUID();
    ProjectUpsertDto dto =
        ProjectUpsertDto.builder()
            .name("Awesome Project")
            .description("A project that does amazing things")
            .topics(List.of("Java", "Spring", "Microservices"))
            .url("https://github.com/awesome-project")
            .website("https://awesome-project.com")
            .build();

    ProjectModel model = mapper.fromCreateDtoToModel(dto, userId);

    Assertions.assertNotNull(model);
    Assertions.assertEquals(dto.getName(), model.getName());
    Assertions.assertEquals(dto.getDescription(), model.getDescription());
    Assertions.assertEquals(dto.getTopics(), model.getTopics());
    Assertions.assertEquals(dto.getUrl(), model.getUrl());
    Assertions.assertEquals(dto.getWebsite(), model.getWebsite());
    Assertions.assertNull(model.getId());
    Assertions.assertEquals(userId, model.getUserId());
  }

  @Test
  void fromCreateDtosToModels() {
    UUID userId = UUID.randomUUID();
    List<ProjectUpsertDto> dtos =
        List.of(
            ProjectUpsertDto.builder()
                .name("Project One")
                .description("First project")
                .topics(List.of("React", "TypeScript"))
                .url("https://github.com/project-one")
                .build(),
            ProjectUpsertDto.builder()
                .name("Project Two")
                .description("Second project")
                .topics(List.of("Java", "Spring Boot"))
                .website("https://project-two.com")
                .build());

    List<ProjectModel> models = mapper.fromCreateDtosToModels(dtos, userId);

    Assertions.assertEquals(dtos.size(), models.size(), "Should map all DTOs to models");

    ProjectModel model1 = models.getFirst();
    ProjectUpsertDto dto1 = dtos.getFirst();
    Assertions.assertEquals(dto1.getName(), model1.getName());
    Assertions.assertEquals(dto1.getDescription(), model1.getDescription());
    Assertions.assertEquals(dto1.getTopics(), model1.getTopics());
    Assertions.assertEquals(dto1.getUrl(), model1.getUrl());
    Assertions.assertEquals(dto1.getWebsite(), model1.getWebsite());
    Assertions.assertNull(model1.getId());
    Assertions.assertEquals(userId, model1.getUserId());

    ProjectModel model2 = models.get(1);
    ProjectUpsertDto dto2 = dtos.get(1);
    Assertions.assertEquals(dto2.getName(), model2.getName());
    Assertions.assertEquals(dto2.getDescription(), model2.getDescription());
    Assertions.assertEquals(dto2.getTopics(), model2.getTopics());
    Assertions.assertEquals(dto2.getUrl(), model2.getUrl());
    Assertions.assertEquals(dto2.getWebsite(), model2.getWebsite());
    Assertions.assertNull(model2.getId());
    Assertions.assertEquals(userId, model2.getUserId());
  }

  @Test
  void updateModelFromDto() {
    ProjectModel model = new ProjectModel();
    model.setId(UUID.randomUUID());
    model.setUserId(UUID.randomUUID());
    model.setName("Old Project");
    model.setDescription("Original description");
    model.setTopics(new ArrayList<>(List.of("Legacy")));
    model.setUrl("https://old-project.com");
    model.setWebsite(null);

    ProjectUpsertDto dto =
        ProjectUpsertDto.builder()
            .name("Updated Project")
            .description("New and improved description")
            .topics(List.of("Modern", "Tech"))
            .website("https://updated-project.com")
            .build();

    ProjectModel updatedModel = mapper.updateModelFromDto(dto, model);

    Assertions.assertSame(model, updatedModel);
    Assertions.assertEquals(model.getId(), updatedModel.getId());
    Assertions.assertEquals(model.getUserId(), updatedModel.getUserId());
    Assertions.assertEquals(dto.getName(), updatedModel.getName());
    Assertions.assertEquals(dto.getDescription(), updatedModel.getDescription());
    Assertions.assertEquals(dto.getTopics(), updatedModel.getTopics());
    Assertions.assertEquals(model.getUrl(), updatedModel.getUrl());
    Assertions.assertEquals(dto.getWebsite(), updatedModel.getWebsite());
  }
}
