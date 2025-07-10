package dyhb.api.database.repository.jpa;

import dyhb.api.database.models.JobModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JobJpaRepository extends JpaRepository<JobModel, UUID> {
    List<JobModel> findByUserId(UUID userId);
}
