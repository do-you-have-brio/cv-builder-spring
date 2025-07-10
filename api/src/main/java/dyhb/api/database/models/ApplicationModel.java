package dyhb.api.database.models;

import dyhb.api.database.models.base.BaseModel;
import jakarta.persistence.*;
import java.util.*;
import lombok.*;

@Data
@Entity
@Table(name = "applications")
@EqualsAndHashCode(callSuper = true)
public class ApplicationModel extends BaseModel {
  private String title;
  private String description;

  // if resume is not present, status is PENDING, otherwise it is CREATED
  // @todo: relationship resume
}
