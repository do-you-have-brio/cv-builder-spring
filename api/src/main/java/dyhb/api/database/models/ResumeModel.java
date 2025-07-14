package dyhb.api.database.models;

import dyhb.api.database.models.base.BaseModel;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "resumes")
@EqualsAndHashCode(callSuper = true)
public class ResumeModel extends BaseModel {
  private String name;
  
  private String url;
}
