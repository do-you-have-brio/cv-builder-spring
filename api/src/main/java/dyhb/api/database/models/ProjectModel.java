package dyhb.api.database.models;

import dyhb.api.database.models.base.BaseModel;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@Table(name = "projects")
@EqualsAndHashCode(callSuper = true)
public class ProjectModel extends BaseModel {
  private String name;

  private String description;

  private List<String> topics;

  private String url;

  private String website;
}
