package dyhb.api.dto;

import dyhb.api.database.models.enums.EmploymentType;
import lombok.*;
import java.util.*;

@Getter
@Builder(toBuilder = true)
public class EmploymentUpsertDto {
  private String title;
  private String company;
  private String location;
  private EmploymentType type;
  private List<String> skills;
  private String description;
  private Date startDate;
  private Date endDate;
}
