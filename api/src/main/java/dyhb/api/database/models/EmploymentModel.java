package dyhb.api.database.models;

import dyhb.api.database.models.base.BaseModel;
import dyhb.api.database.models.enums.EmploymentType;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Data
@Entity
@Table(name = "employments")
@EqualsAndHashCode(callSuper = true)
public class EmploymentModel extends BaseModel {
  private String title;

  private String company;

  private String location;

  private EmploymentType type;

  private List<String> skills;

  private String description;

  @Column(name = "start_date")
  private Date startDate;

  @Column(name = "end_date")
  private Date endDate; // null if ongoing
}
