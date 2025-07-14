package dyhb.api.database.repository.jpa;

import dyhb.api.database.models.EmploymentModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface EmploymentJpaRepository extends JpaRepository<EmploymentModel, UUID> {
  List<EmploymentModel> findByUserId(UUID userId);

  @Modifying
  @Transactional
  @Query(value = "DELETE FROM EmploymentModel j WHERE j.id = :id")
  int deleteByUuid(@Param("id") UUID id);
}
