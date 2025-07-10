package dyhb.api.database.models;

import dyhb.api.database.models.base.BaseModel;
import dyhb.api.database.models.enums.SocialAccountType;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(
    name = "social_accounts",
    uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "type"}))
@EqualsAndHashCode(callSuper = true)
public class SocialAccount extends BaseModel {
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private SocialAccountType type;

  @Column(nullable = false)
  private String url;
}
