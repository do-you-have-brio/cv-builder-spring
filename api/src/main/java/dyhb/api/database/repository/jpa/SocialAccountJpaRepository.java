package dyhb.api.database.repository.jpa;

import dyhb.api.database.models.SocialAccountModel;
import jakarta.transaction.Transactional;
import java.util.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface SocialAccountJpaRepository extends JpaRepository<SocialAccountModel, UUID> {
  List<SocialAccountModel> findByUserId(UUID userId);

  @Modifying
  @Transactional
  @Query(value = "DELETE FROM SocialAccountModel e WHERE e.id = :id")
  int deleteByUuid(@Param("id") UUID id);
}
