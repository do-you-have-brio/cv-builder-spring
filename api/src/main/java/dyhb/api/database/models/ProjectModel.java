package dyhb.api.database.models;

import dyhb.api.database.models.base.BaseModel;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "projects")
@EqualsAndHashCode(callSuper = true)
public class ProjectModel extends BaseModel {
  private String name;
  private String description;
}
