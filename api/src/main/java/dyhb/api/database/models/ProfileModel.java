package dyhb.api.database.models;

import dyhb.api.database.models.base.BaseModel;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "profiles")
@EqualsAndHashCode(callSuper = true)
public class ProfileModel extends BaseModel {
  private String name;

  @Column(name = "avatar_url")
  private String avatarUrl;
}
