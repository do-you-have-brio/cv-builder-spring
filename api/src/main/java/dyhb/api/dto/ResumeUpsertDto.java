package dyhb.api.dto;

import lombok.*;

@Getter
@Builder(toBuilder = true)
public class ResumeUpsertDto {
  private String name;
  private String url;
}
