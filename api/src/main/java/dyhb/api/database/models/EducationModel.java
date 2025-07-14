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

  @Enumerated(EnumType.STRING)
  @Column(name = "education_degree", nullable = false)
  private EducationDegree educationDegree;

  private String institution;

  private String description;

  @Column(name = "start_date")
  private Date startDate;

  @Column(name = "end_date")
  private Date endDate; // null if ongoing
}
