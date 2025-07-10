package dyhb.api.database.repository;

import dyhb.api.database.models.Projects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProjectsRepository extends JpaRepository<Projects, UUID> {
    List<Projects> findByUserId(UUID userId);
}
