package dyhb.api.database.repository;

import dyhb.api.database.models.EducationModel;
import java.util.*;
import dyhb.api.database.repository.jpa.EducationJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EducationRepository {

  @Autowired private final EducationJpaRepository jpaRepository;

  public Optional<EducationModel> findById(UUID id) {
    return jpaRepository.findById(id);
  }

  public List<EducationModel> findByUserId(UUID userId) {
    return jpaRepository.findByUserId(userId);
  }

  public EducationModel save(EducationModel model) {
    return jpaRepository.save(model);
  }

  public List<EducationModel> saveAll(List<EducationModel> models) {
    return jpaRepository.saveAll(models);
  }

  public boolean delete(UUID id) {
    return jpaRepository.deleteByUuid(id) == 1;
  }
}
