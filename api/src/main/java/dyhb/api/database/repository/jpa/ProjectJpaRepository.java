package dyhb.api.database.repository.jpa;

import dyhb.api.database.models.ProjectModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProjectJpaRepository extends JpaRepository<ProjectModel, UUID> {
    List<ProjectModel> findByUserId(UUID userId);
}
