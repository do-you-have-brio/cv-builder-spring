package dyhb.api.dto;

import lombok.*;

@Getter
@Builder(toBuilder = true)
public class ProjectUpsertDto {
  private String name;
  private String description;
}
