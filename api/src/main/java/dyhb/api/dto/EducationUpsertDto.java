package dyhb.api.dto;

import dyhb.api.database.models.enums.EducationDegree;
import lombok.*;
import java.util.*;

@Getter
@Builder(toBuilder = true)
public class EducationUpsertDto {
  private String name;
  private EducationDegree degree;
  private String institution;
  private String description;
  private Date startDate;
  private Date endDate;
}
