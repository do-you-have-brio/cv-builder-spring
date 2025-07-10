package dyhb.api.mappers;

import dyhb.api.database.models.SocialAccountModel;
import dyhb.api.dto.SocialAccountUpsertDto;
import dyhb.api.database.models.enums.SocialAccountType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.*;

public class SocialAccountMapperTest {

  private final SocialAccountMapper mapper = Mappers.getMapper(SocialAccountMapper.class);

  @Test
  void fromCreateDtoToModel() {
    UUID userId = UUID.randomUUID();
    SocialAccountUpsertDto dto =
        SocialAccountUpsertDto.builder()
            .socialAccountType(SocialAccountType.GITHUB)
            .url("https://github.com/user")
            .build();

    SocialAccountModel model = mapper.fromCreateDtoToModel(dto, userId);

    Assertions.assertNotNull(model);
    Assertions.assertEquals(dto.getSocialAccountType(), model.getSocialAccountType());
    Assertions.assertEquals(dto.getUrl(), model.getUrl());
    Assertions.assertNull(model.getId());
    Assertions.assertEquals(userId, model.getUserId());
  }

  @Test
  void fromCreateDtosToModels() {
    UUID userId = UUID.randomUUID();
    List<SocialAccountUpsertDto> dtos =
        List.of(
            SocialAccountUpsertDto.builder()
                .socialAccountType(SocialAccountType.LINKEDIN)
                .url("https://linkedin.com/in/user")
                .build(),
            SocialAccountUpsertDto.builder()
                .socialAccountType(SocialAccountType.INSTAGRAM)
                .url("https://twitter.com/user")
                .build());

    List<SocialAccountModel> models = mapper.fromCreateDtosToModels(dtos, userId);

    Assertions.assertEquals(dtos.size(), models.size(), "Should map all DTOs to models");

    SocialAccountModel model1 = models.getFirst();
    SocialAccountUpsertDto dto1 = dtos.getFirst();
    Assertions.assertEquals(dto1.getSocialAccountType(), model1.getSocialAccountType());
    Assertions.assertEquals(dto1.getUrl(), model1.getUrl());
    Assertions.assertNull(model1.getId());
    Assertions.assertEquals(userId, model1.getUserId());

    SocialAccountModel model2 = models.get(1);
    SocialAccountUpsertDto dto2 = dtos.get(1);
    Assertions.assertEquals(dto2.getSocialAccountType(), model2.getSocialAccountType());
    Assertions.assertEquals(dto2.getUrl(), model2.getUrl());
    Assertions.assertNull(model2.getId());
    Assertions.assertEquals(userId, model2.getUserId());
  }

  @Test
  void updateModelFromDto() {
    SocialAccountModel model = new SocialAccountModel();
    model.setId(UUID.randomUUID());
    model.setUserId(UUID.randomUUID());
    model.setSocialAccountType(SocialAccountType.FACEBOOK);
    model.setUrl("https://facebook.com/old");

    SocialAccountUpsertDto dto =
        SocialAccountUpsertDto.builder().socialAccountType(SocialAccountType.INSTAGRAM).build();

    SocialAccountModel updatedModel = mapper.updateModelFromDto(dto, model);

    Assertions.assertSame(model, updatedModel);
    Assertions.assertEquals(model.getId(), updatedModel.getId());
    Assertions.assertEquals(model.getUserId(), updatedModel.getUserId());
    Assertions.assertEquals(dto.getSocialAccountType(), updatedModel.getSocialAccountType());
    Assertions.assertEquals(model.getUrl(), updatedModel.getUrl());
  }
}
