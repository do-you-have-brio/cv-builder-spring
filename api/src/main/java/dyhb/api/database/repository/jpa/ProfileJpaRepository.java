package dyhb.api.database.repository.jpa;

import dyhb.api.database.models.ProfileModel;
import jakarta.transaction.Transactional;
import java.util.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface ProfileJpaRepository extends JpaRepository<ProfileModel, UUID> {
  Optional<ProfileModel> findByUserId(UUID userId);

  @Modifying
  @Transactional
  @Query(value = "DELETE FROM ProfileModel e WHERE e.id = :id")
  int deleteByUuid(@Param("id") UUID id);
}
