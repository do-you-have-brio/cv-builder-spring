package dyhb.api.dto;

import lombok.*;
import java.util.*;

@Getter
@Builder(toBuilder = true)
public class ProjectUpsertDto {
  private String name;
  private String description;
  private List<String> topics;
  private String url;
  private String website;
}
