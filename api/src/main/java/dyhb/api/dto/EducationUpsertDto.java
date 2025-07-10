package dyhb.api.dto;

import lombok.*;
import java.util.*;

@Getter
@Builder(toBuilder = true)
public class EducationUpsertDto {
  private String institution;
  private String degree;
  private Date startDate;
  private Date endDate;
  private String description;
}
