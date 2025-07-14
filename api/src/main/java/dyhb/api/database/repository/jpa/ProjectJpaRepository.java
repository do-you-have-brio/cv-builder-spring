package dyhb.api.database.repository.jpa;

import dyhb.api.database.models.ProjectModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface ProjectJpaRepository extends JpaRepository<ProjectModel, UUID> {
  List<ProjectModel> findByUserId(UUID userId);

  @Modifying
  @Transactional
  @Query(value = "DELETE FROM ProjectModel e WHERE e.id = :id")
  int deleteByUuid(@Param("id") UUID id);
}
