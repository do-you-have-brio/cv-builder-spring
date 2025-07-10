package dyhb.api.service;

import dyhb.api.database.models.ProjectModel;
import java.util.*;
import dyhb.api.database.repository.jpa.ProjectJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProjectService {

  @Autowired private final ProjectJpaRepository jpaRepository;

  public Optional<ProjectModel> findById(UUID id) {
    return jpaRepository.findById(id);
  }

  public List<ProjectModel> findByUserId(UUID userId) {
    return jpaRepository.findByUserId(userId);
  }

  public ProjectModel save(ProjectModel model) {
    return jpaRepository.save(model);
  }

  public List<ProjectModel> saveAll(List<ProjectModel> models) {
    return jpaRepository.saveAll(models);
  }

  public boolean delete(UUID id) {
    return jpaRepository.deleteByUuid(id) == 1;
  }
}
