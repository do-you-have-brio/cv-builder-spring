package dyhb.api.database.repository.jpa;

import dyhb.api.database.models.JobModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface JobJpaRepository extends JpaRepository<JobModel, UUID> {
  List<JobModel> findByUserId(UUID userId);

  @Modifying
  @Transactional
  @Query(value = "DELETE FROM JobModel j WHERE j.id = :id")
  int deleteByUuid(@Param("id") UUID id);
}
