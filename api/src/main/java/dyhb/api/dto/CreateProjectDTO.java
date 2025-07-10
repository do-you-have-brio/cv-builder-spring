package dyhb.api.dto;

import lombok.*;

@Getter
@Builder(toBuilder = true)
public class CreateProjectDTO {
  private String name;
  private String description;
}
