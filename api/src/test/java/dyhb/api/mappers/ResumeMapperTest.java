package dyhb.api.mappers;

import dyhb.api.database.models.ResumeModel;
import dyhb.api.dto.ResumeUpsertDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.*;

public class ResumeMapperTest {

  private final ResumeMapper mapper = Mappers.getMapper(ResumeMapper.class);

  @Test
  void fromCreateDtoToModel() {
    UUID userId = UUID.randomUUID();
    ResumeUpsertDto dto =
        ResumeUpsertDto.builder()
            .name("John Doe Resume")
            .url("https://example.com/resume.pdf")
            .build();

    ResumeModel model = mapper.fromCreateDtoToModel(dto, userId);

    Assertions.assertNotNull(model);
    Assertions.assertEquals(dto.getName(), model.getName());
    Assertions.assertEquals(dto.getUrl(), model.getUrl());
    Assertions.assertNull(model.getId());
    Assertions.assertEquals(userId, model.getUserId());
  }

  @Test
  void fromCreateDtosToModels() {
    UUID userId = UUID.randomUUID();
    List<ResumeUpsertDto> dtos =
        List.of(
            ResumeUpsertDto.builder().name("Dev Resume").url("https://example.com/dev.pdf").build(),
            ResumeUpsertDto.builder()
                .name("Design Resume")
                .url("https://example.com/design.pdf")
                .build());

    List<ResumeModel> models = mapper.fromCreateDtosToModels(dtos, userId);

    Assertions.assertEquals(dtos.size(), models.size(), "Should map all DTOs to models");

    ResumeModel model1 = models.getFirst();
    ResumeUpsertDto dto1 = dtos.getFirst();
    Assertions.assertEquals(dto1.getName(), model1.getName());
    Assertions.assertEquals(dto1.getUrl(), model1.getUrl());
    Assertions.assertNull(model1.getId());
    Assertions.assertEquals(userId, model1.getUserId());

    ResumeModel model2 = models.get(1);
    ResumeUpsertDto dto2 = dtos.get(1);
    Assertions.assertEquals(dto2.getName(), model2.getName());
    Assertions.assertEquals(dto2.getUrl(), model2.getUrl());
    Assertions.assertNull(model2.getId());
    Assertions.assertEquals(userId, model2.getUserId());
  }

  @Test
  void updateModelFromDto() {
    ResumeModel model = new ResumeModel();
    model.setId(UUID.randomUUID());
    model.setUserId(UUID.randomUUID());
    model.setName("Old Resume");
    model.setUrl("https://old.com/resume.pdf");

    ResumeUpsertDto dto = ResumeUpsertDto.builder().name("Updated Resume").build();

    ResumeModel updatedModel = mapper.updateModelFromDto(dto, model);

    Assertions.assertSame(model, updatedModel);
    Assertions.assertEquals(model.getId(), updatedModel.getId());
    Assertions.assertEquals(model.getUserId(), updatedModel.getUserId());
    Assertions.assertEquals(dto.getName(), updatedModel.getName());
    Assertions.assertEquals(model.getUrl(), updatedModel.getUrl());
  }
}
