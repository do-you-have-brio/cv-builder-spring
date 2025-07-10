package dyhb.api.dto;

import lombok.*;

@Getter
@Builder(toBuilder = true)
public class CreateResumeDTO {
  private String name;
  private String link;
}
