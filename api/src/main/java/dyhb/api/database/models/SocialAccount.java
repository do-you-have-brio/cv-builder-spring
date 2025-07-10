package dyhb.api.database.models;

import dyhb.api.database.models.base.BaseModel;
import dyhb.api.database.models.enums.SocialAccountType;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "social_accounts")
@EqualsAndHashCode(callSuper = true)
public class SocialAccount extends BaseModel {
  private SocialAccountType type;
  private String url;
}
