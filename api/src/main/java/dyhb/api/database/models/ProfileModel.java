package dyhb.api.database.models;

import dyhb.api.database.models.base.BaseModel;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "profiles", uniqueConstraints = @UniqueConstraint(columnNames = "user_id"))
@EqualsAndHashCode(callSuper = true)
public class ProfileModel extends BaseModel {
  private String name;

  @Column(name = "avatar_url")
  private String avatarUrl;
}
