package dyhb.api.database.repository.jpa;

import dyhb.api.database.models.EducationModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.*;

public interface EducationJpaRepository extends JpaRepository<EducationModel, UUID> {
  List<EducationModel> findByUserId(UUID userId);

  @Modifying
  @Transactional
  @Query(value = "DELETE FROM EducationModel e WHERE e.id = :id")
  int deleteByUuid(@Param("id") UUID id);
}
