package dyhb.api.dto;

import lombok.*;
import java.util.*;

@Getter
@Builder(toBuilder = true)
public class CreateEducationDTO {
  private String institution;
  private String degree;
  private String fieldOfStudy;
  private Date startDate;
  private Date endDate;
  private String description;
}
