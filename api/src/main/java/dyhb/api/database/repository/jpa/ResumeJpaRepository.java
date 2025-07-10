package dyhb.api.database.repository.jpa;

import dyhb.api.database.models.ResumeModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface ResumeJpaRepository extends JpaRepository<ResumeModel, UUID> {
  List<ResumeModel> findByUserId(UUID userId);

  @Modifying
  @Transactional
  @Query(value = "DELETE FROM ResumeModel e WHERE e.id = :id")
  int deleteByUuid(@Param("id") UUID id);
}
