package dyhb.api.dto;

import dyhb.api.database.models.enums.SocialAccountType;
import lombok.*;

@Getter
@Builder(toBuilder = true)
public class SocialAccountUpsertDto {
  private SocialAccountType socialAccountType;
  private String url;
}
