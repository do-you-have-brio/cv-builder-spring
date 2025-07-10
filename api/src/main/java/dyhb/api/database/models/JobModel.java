package dyhb.api.database.models;

import dyhb.api.database.models.base.BaseModel;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Data
@Entity
@Table(name = "jobs")
@EqualsAndHashCode(callSuper = true)
public class JobModel extends BaseModel {
  private String title;
  private String company;
  private String description;

  @Column(name = "start_date")
  private Date startDate;

  @Column(name = "end_date")
  private Date endDate;
}
