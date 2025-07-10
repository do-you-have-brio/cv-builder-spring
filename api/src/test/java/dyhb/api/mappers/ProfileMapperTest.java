package dyhb.api.mappers;

import dyhb.api.database.models.ProfileModel;
import dyhb.api.dto.ProfileUpsertDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.*;

public class ProfileMapperTest {

  private final ProfileMapper mapper = Mappers.getMapper(ProfileMapper.class);

  @Test
  void fromCreateDtoToModel() {
    UUID userId = UUID.randomUUID();
    ProfileUpsertDto dto =
        ProfileUpsertDto.builder()
            .name("John Doe")
            .avatarUrl("https://example.com/avatar.jpg")
            .build();

    ProfileModel model = mapper.fromCreateDtoToModel(dto, userId);

    Assertions.assertNotNull(model);
    Assertions.assertEquals(dto.getName(), model.getName());
    Assertions.assertEquals(dto.getAvatarUrl(), model.getAvatarUrl());
    Assertions.assertNull(model.getId());
    Assertions.assertEquals(userId, model.getUserId());
  }

  @Test
  void updateModelFromDto() {
    ProfileModel model = new ProfileModel();
    model.setId(UUID.randomUUID());
    model.setUserId(UUID.randomUUID());
    model.setName("Original Name");
    model.setAvatarUrl("https://example.com/original.jpg");

    ProfileUpsertDto dto =
        ProfileUpsertDto.builder()
            .name("Updated Name")
            .avatarUrl("https://example.com/updated.jpg")
            .build();

    ProfileModel updatedModel = mapper.updateModelFromDto(dto, model);

    Assertions.assertSame(model, updatedModel);
    Assertions.assertEquals(model.getId(), updatedModel.getId());
    Assertions.assertEquals(model.getUserId(), updatedModel.getUserId());
    Assertions.assertEquals(dto.getName(), updatedModel.getName());
    Assertions.assertEquals(dto.getAvatarUrl(), updatedModel.getAvatarUrl());
  }
}
