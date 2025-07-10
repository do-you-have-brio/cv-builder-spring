package dyhb.api.database.repository.jpa;

import dyhb.api.database.models.ResumeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ResumeJpaRepository extends JpaRepository<ResumeModel, UUID> {
    List<ResumeModel> findByUserId(UUID userId);
}
