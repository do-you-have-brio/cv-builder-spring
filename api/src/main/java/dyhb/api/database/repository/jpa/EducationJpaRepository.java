package dyhb.api.database.repository.jpa;

import dyhb.api.database.models.EducationModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EducationJpaRepository extends JpaRepository<EducationModel, UUID> {
    List<EducationModel> findByUserId(UUID userId);
}
