package dyhb.api.database.models.base;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.*;
import java.util.*;

@Data
@MappedSuperclass
public abstract class BaseModel {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(unique = true, updatable = false, nullable = false, columnDefinition = "uuid")
  private UUID id;

  @Column(name = "user_id", nullable = false)
  private UUID userId;

  @CreationTimestamp
  @Column(name = "created_at")
  private Date createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private Date updatedAt;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof BaseModel that)) return false;
    return Objects.equals(id, that.id) && Objects.equals(userId, that.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, userId);
  }
}
