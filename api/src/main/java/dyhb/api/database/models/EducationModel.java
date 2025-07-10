package dyhb.api.database.models;

import dyhb.api.database.models.base.BaseModel;
import dyhb.api.database.models.enums.EducationDegree;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Data
@Entity
@Table(name = "educations")
@EqualsAndHashCode(callSuper = true)
public class EducationModel extends BaseModel {
  private String name;
  private EducationDegree degree;
  private String institution;
  private String description;

  @Column(name = "start_date")
  private Date startDate;

  @Column(name = "end_date")
  private Date endDate; // null if ongoing
}
