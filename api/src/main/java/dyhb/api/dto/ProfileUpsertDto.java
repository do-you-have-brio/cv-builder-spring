package dyhb.api.dto;

import lombok.*;

@Getter
@Builder(toBuilder = true)
public class ProfileUpsertDto {
  private String name;
  private String avatarUrl;
}
